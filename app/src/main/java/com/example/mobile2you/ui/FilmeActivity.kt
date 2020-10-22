package com.example.mobile2you.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile2you.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_filme_principal.*


class FilmeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Tirar a app title bar
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_filme_principal)

        //pega imagem da internet
        Picasso.get()
            .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2/pci1ArYW7oJ2eyTo2NMYEKHHiCP.jpg")
            .into(
                activity_filme_principal_image_view_principal
            )


        //botao de favorito
        var favoritado = false
        val botaoFavorito =
            findViewById<ImageButton>(R.id.activity_filme_principal_image_button_favorite)
        botaoFavorito.setOnClickListener {

            botaoFavorito.isSelected = !botaoFavorito.isPressed()

            if (botaoFavorito.isPressed && !favoritado) {
                botaoFavorito.setImageResource(R.drawable.ic_baseline_favorite_24)
                favoritado = !favoritado
            }

            else if (botaoFavorito.isPressed && favoritado){
                botaoFavorito.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                favoritado = !favoritado
            }

            else {
                botaoFavorito.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }
    }

}