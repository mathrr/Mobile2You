package com.example.mobile2you.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile2you.R

class FilmeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Tirar a app title bar
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_filme_principal)

    }

}