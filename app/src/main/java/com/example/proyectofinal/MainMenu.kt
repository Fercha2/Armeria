package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
    }

    fun alta(view: View) {
        val intent = Intent(this, MainAltas::class.java)
        startActivity(intent)
    }

    fun baja(view: View) {
        val intent = Intent(this, MainBajas::class.java)
        startActivity(intent)
    }

    fun cambios(view: View) {
        val intent = Intent(this, MainCambios::class.java)
        startActivity(intent)
    }

    fun consultas(view: View) {
        val intent = Intent(this, MainConsultas::class.java)
        startActivity(intent)
    }

    fun salir(view: View){
        finishAffinity()
    }

}