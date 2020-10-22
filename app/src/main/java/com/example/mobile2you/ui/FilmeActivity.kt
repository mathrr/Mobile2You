package com.example.mobile2you.ui

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile2you.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_filme_principal.*


class FilmeActivity : AppCompatActivity(){

    var URLBase = "https://api.themoviedb.org/"

    var favoritado = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tiraAppTitleBar()

        setContentView(R.layout.activity_filme_principal)

        //pega imagem da internet
        Picasso.get()
            .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2/gajva2L0rPYkEWjzgFlBXCAVBE5.jpg")
            .into(
                activity_filme_principal_image_view_poster_filme_principal
            )

        configuraBotaoFavoritar()

    }

    private fun tiraAppTitleBar() {
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
    }

    private fun configuraBotaoFavoritar() {
        val botaoFavorito =
            findViewById<ImageButton>(R.id.activity_filme_principal_image_button_favorito)
        botaoFavorito.setOnClickListener {

            botaoFavorito.isSelected = !botaoFavorito.isPressed()

            if (botaoFavorito.isPressed && !favoritado) {
                botaoFavorito.setImageResource(R.drawable.ic_baseline_favorite_24)
                favoritado = !favoritado
            } else if (botaoFavorito.isPressed && favoritado) {
                botaoFavorito.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                favoritado = !favoritado
            } else {
                botaoFavorito.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }
    }

}