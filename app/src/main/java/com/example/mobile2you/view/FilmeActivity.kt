package com.example.mobile2you.view

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile2you.R
import com.example.mobile2you.adapter.FilmesSimilaresAdapter
import com.example.mobile2you.adapter.configuraBotaoFavoritar
import com.example.mobile2you.adapter.configuraFilmePrincipalNoLayout


class FilmeActivity : AppCompatActivity() {

    private lateinit var recyclerViewFilmesSimilares: RecyclerView
    private lateinit var filmesSimilaresAdapter: FilmesSimilaresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_filme_principal)

        val nomeFilmePrincipal: TextView = findViewById(R.id.activity_filme_principal_nome_do_filme)
        val popularidade: TextView =
            findViewById(R.id.activity_filme_principal_text_view_popularidade)
        val votos: TextView = findViewById(R.id.activity_filme_principal_text_view_votos)
        val poster: ImageView =
            findViewById(R.id.activity_filme_principal_image_view_poster_filme_principal)
        val botaoLike: ImageButton =
            findViewById(R.id.activity_filme_principal_image_button_favorito)

        configuraFilmePrincipalNoLayout(nomeFilmePrincipal, popularidade, votos, poster)

        configuraReclyclerView()

        filmesSimilaresAdapter.configuraFilmesSimilaresNoLayout()

        configuraBotaoFavoritar(botaoLike, votos)

    }

    private fun configuraReclyclerView() {
        recyclerViewFilmesSimilares =
            findViewById(R.id.activity_filme_principal_recyclerview_filmes_similares)
        recyclerViewFilmesSimilares.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        filmesSimilaresAdapter = FilmesSimilaresAdapter(listOf())
        recyclerViewFilmesSimilares.adapter = filmesSimilaresAdapter
    }

}