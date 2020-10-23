package com.example.mobile2you.view

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile2you.R
import com.example.mobile2you.model.Filme
import com.example.mobile2you.retrofit.FilmeRetrofit
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_filme_principal.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FilmeActivity : AppCompatActivity() {

    var favoritado = false

    var brfilmeID: String = "335984"

    var posterURLBASE: String = "https://image.tmdb.org/t/p/w600_and_h900_bestv2"

    var numeroDeVotos: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_filme_principal)

        var call: Call<Filme> = FilmeRetrofit().apiInterface.pegaFilme(brfilmeID)
        call.enqueue(object : Callback<Filme> {
            override fun onFailure(call: Call<Filme>?, t: Throwable?) {
                onError()
            }

            override fun onResponse(call: Call<Filme>?, response: Response<Filme>?) {
                var filme = response?.body()!!

                configuraNomeDoFilme(filme)

                configuraPosterDoFilme(filme)

                configuraViewsDoFilme(filme)

                configuraNumeroDeLikes(filme.numeroDeVotos)

            }

        })

        configuraBotaoFavoritar()

    }

    private fun configuraViewsDoFilme(filme: Filme) {
        var popularidade =
            findViewById<TextView>(R.id.activity_filme_principal_text_view_popularidade)
        popularidade.setText(filme.popularidade.toString() + " views")
    }

    private fun configuraNomeDoFilme(filme: Filme) {
        val nomeFilmePrincipal = findViewById<TextView>(R.id.activity_filme_principal_nome_do_filme)
        nomeFilmePrincipal.setText(filme.titulo)
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
        var votos = findViewById<TextView>(R.id.acitivity_filme_principal_text_view_votos)
        var votoTexto = vote_count.toString() + " likes"
        votos.setText(votoTexto)
    }

    private fun onError() {
        Toast.makeText(this, getString(R.string.erro_carregar_filme), Toast.LENGTH_SHORT).show()
    }

    private fun configuraBotaoFavoritar() {
        val botaoFavorito =
            findViewById<ImageButton>(R.id.activity_filme_principal_image_button_favorito)
        botaoFavorito.setOnClickListener {

            botaoFavorito.isSelected = !botaoFavorito.isPressed()

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