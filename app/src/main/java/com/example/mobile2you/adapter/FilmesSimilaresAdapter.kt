package com.example.mobile2you.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile2you.R
import com.example.mobile2you.model.Filme
import com.squareup.picasso.Picasso

class FilmesSimilaresAdapter (
    private var filmes: List<Filme>
) : RecyclerView.Adapter<FilmesSimilaresAdapter.MovieViewHolder>() {

    private var posterURLBASE: String = "https://image.tmdb.org/t/p/w600_and_h900_bestv2"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.filme_similar_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = filmes.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(filmes[position])
    }

    fun updateMovies(movies: List<Filme>) {
        this.filmes = movies
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val poster: ImageView = itemView.findViewById(R.id.filme_similar_item_imageview_poster)
        private val titulo: TextView = itemView.findViewById(R.id.filme_similar_item_textview_titulo)
        private var ano : TextView = itemView.findViewById(R.id.filme_similar_item_textview_ano)
//        private var genero : TextView = itemView.findViewById(R.id.filme_similar_item_textview_genero)


        fun bind(filme: Filme) {
            Picasso.get().load(posterURLBASE + filme.posterPath).into(poster)
            titulo.text = filme.titulo

            ano.text  = filme.dataDeLancamento.substring(0,4)

        }
    }
}