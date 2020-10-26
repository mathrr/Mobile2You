package com.example.mobile2you.adapter

import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.mobile2you.R
import com.example.mobile2you.model.Filme
import com.example.mobile2you.retrofit.FilmeRetrofit
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmeAdapter {

}

private var movieID: String = "335984"

private val apikey: String = "5dc5091fc0142ab27175fd181084427b"

private val posterURLBASE: String = "https://image.tmdb.org/t/p/original/"

private var numeroDeVotos: Int = 0

private var favoritado = false

fun configuraViewsDoFilme(viewsDoFilme: TextView, filme: Filme) {
    val viewsDoFilmeFormatado = "${filme.popularidade.toString()} views"
    viewsDoFilme.text = viewsDoFilmeFormatado
}

fun configuraNomeDoFilme(nomeDoFilme: TextView, filme: Filme) {
    nomeDoFilme.text = filme.titulo
}

fun configuraPosterDoFilme(poster: ImageView, filme: Filme) {
    Picasso.get()
        .load(posterURLBASE + filme.posterPath)
        .into(poster)
}

fun configuraNumeroDeLikes(votos: TextView, vote_count: Int) {
    numeroDeVotos = vote_count
    val votoTexto = "$vote_count likes"
    votos.text = votoTexto
}

fun configuraBotaoFavoritar(botaoFavorito: ImageButton, votos: TextView) {
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
        configuraNumeroDeLikes(votos, numeroDeVotos)
    }
}

fun configuraFilmePrincipalNoLayout(
    nome: TextView,
    popularidade: TextView,
    votos: TextView,
    poster: ImageView
) {
    val call: Call<Filme> = FilmeRetrofit().apiInterface.getFilme(movieID, apikey)
    call.enqueue(object : Callback<Filme> {
        override fun onFailure(call: Call<Filme>?, t: Throwable?) {
            onError()
        }

        override fun onResponse(call: Call<Filme>?, response: Response<Filme>?) {
            val filme = response?.body()!!

            configuraNomeDoFilme(nome, filme)

            configuraPosterDoFilme(poster, filme)

            configuraViewsDoFilme(popularidade, filme)

            configuraNumeroDeLikes(
                votos,
                filme.numeroDeVotos
            )

        }

    })
}

private fun onError() {
//    Toast.makeText(R.layout.activity_filme_principal, getString(R.string.erro_carregar_filme), Toast.LENGTH_SHORT).show()
}