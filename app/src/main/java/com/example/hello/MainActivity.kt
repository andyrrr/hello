package com.example.hello

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //generateDummyList(15)
    }

    private fun generateDummyList(size: Int) {
        for (i in 0 until size) {
            Inventario.agregarProducto("Camisa $i","segunda Linea",(990.0*i),0)
        }
    }

    fun onClickCotizaciones(v: View){
        val intent = Intent(this, cotizacionesActivity::class.java)
        startActivity(intent)
    }

    fun onClickInventario(v: View){
        val intent = Intent(this, productosActivity::class.java)
        startActivity(intent)
    }
}
