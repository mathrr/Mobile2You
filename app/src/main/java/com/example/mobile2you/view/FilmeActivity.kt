package com.example.mobile2you.view

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile2you.R
import com.example.mobile2you.adapter.FilmesAdapter
import com.example.mobile2you.adapter.FilmesSimilaresAdapter
import com.example.mobile2you.adapter.configuraNomeDoFilme
import com.example.mobile2you.adapter.configuraViewsDoFilme
import com.example.mobile2you.model.Filme
import com.example.mobile2you.model.FilmesSimilares
import com.example.mobile2you.retrofit.FilmeRetrofit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_filme_principal.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FilmeActivity : AppCompatActivity() {

    private var favoritado = false

    private var movieID: String = "335984"

    private var posterURLBASE: String = "https://image.tmdb.org/t/p/original/"

    private var numeroDeVotos: Int = 0

    private lateinit var recyclerViewFilmesSimilares: RecyclerView
    private lateinit var filmesSimilaresAdapter: FilmesSimilaresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_filme_principal)

        val call: Call<Filme> = FilmeRetrofit().apiInterface.getFilme(movieID, getString(R.string.apikey))
        call.enqueue(object : Callback<Filme> {
            override fun onFailure(call: Call<Filme>?, t: Throwable?) {
                onError()
            }

            override fun onResponse(call: Call<Filme>?, response: Response<Filme>?) {
                val filme = response?.body()!!

                configuraNomeDoFilme(activity_filme_principal_nome_do_filme, filme)

                configuraPosterDoFilme(filme)

                configuraViewsDoFilme(activity_filme_principal_text_view_popularidade, filme)

                configuraNumeroDeLikes(filme.numeroDeVotos)

            }

        })

        recyclerViewFilmesSimilares = findViewById(R.id.activity_filme_principal_recyclerview_filmes_similares)
        recyclerViewFilmesSimilares.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        filmesSimilaresAdapter = FilmesSimilaresAdapter(listOf())
        recyclerViewFilmesSimilares.adapter = filmesSimilaresAdapter


        val callSimilares: Call<FilmesSimilares> = FilmeRetrofit().apiInterface.getFilmesSimilares(movieID, getString(R.string.apikey))
        callSimilares.enqueue(object : Callback<FilmesSimilares> {
            override fun onFailure(call: Call<FilmesSimilares>?, t: Throwable?) {
                onError()
            }

            override fun onResponse(
                call: Call<FilmesSimilares>,
                response: Response<FilmesSimilares>
            ) {
                val filmesSimilares = response.body()!!

                filmesSimilaresAdapter.updateMovies(filmesSimilares.filmesSimilares)
            }

        })



        configuraBotaoFavoritar()

    }

    private fun configuraPosterDoFilme(filme: Filme) {
        Picasso.get()
            .load(posterURLBASE + filme.posterPath)
            .into(
                activity_filme_principal_image_view_poster_filme_principal
            )
    }

    private fun configuraNumeroDeLikes(vote_count: Int) {
        numeroDeVotos = vote_count
        val votos = findViewById<TextView>(R.id.activity_filme_principal_text_view_votos)
        val votoTexto = "$vote_count likes"
        votos.text = votoTexto
    }

    private fun onError() {
        Toast.makeText(this, getString(R.string.erro_carregar_filme), Toast.LENGTH_SHORT).show()
    }

    private fun configuraBotaoFavoritar() {
        val botaoFavorito =
            findViewById<ImageButton>(R.id.activity_filme_principal_image_button_favorito)
        botaoFavorito.setOnClickListener {

            botaoFavorito.isSelected = !botaoFavorito.isPressed

            if (botaoFavorito.isPressed && !favoritado) {
                botaoFavorito.setImageResource(R.drawable.ic_baseline_favorite_24)
                favoritado = !favoritado
                numeroDeVotos++
            } else if (botaoFavorito.isPressed && favoritado) {
                botaoFavorito.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                favoritado = !favoritado
                numeroDeVotos--
            } else {
                botaoFavorito.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
            configuraNumeroDeLikes(numeroDeVotos)
        }
    }

}