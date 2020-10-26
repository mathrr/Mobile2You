package com.example.mobile2you.adapter

import android.widget.TextView
import com.example.mobile2you.model.Filme

class FilmesAdapter {

}
fun configuraViewsDoFilme(viewsDoFilme: TextView, filme : Filme) {
    val viewsDoFilmeFormatado = "${filme.popularidade.toString()} views"
    viewsDoFilme.text = viewsDoFilmeFormatado
}

fun configuraNomeDoFilme(nomeDoFilme : TextView, filme: Filme) {
    nomeDoFilme.text = filme.titulo
}