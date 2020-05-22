package com.example.hello

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_cotizaciones.*
import kotlinx.android.synthetic.main.activity_productos.*

class cotizacionesActivity : AppCompatActivity(), OnInventarioItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cotizaciones)

        if (!Inventario.listaCotizacion.none()) {
            val text: TextView = findViewById<TextView>(R.id.tvCotizacionesVacio)
            text.setVisibility(View.GONE);

            recyclerCotizaciones.adapter = adaptadorACotizacion(Inventario.listaCotizacion, this)
            recyclerCotizaciones.layoutManager = LinearLayoutManager(this)
            recyclerCotizaciones.setHasFixedSize(true)
        }
    }


    fun onClickCrearCotizacionNueva(v: View) {
        val intent = Intent(this, crearCotizacionActivity::class.java)
        intent.putExtra("idCotizacion", "0")
        startActivity(intent)
    }

    override fun onProductoClick(item: Producto, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCotizacionClick(item: Cotizacion, position: Int) {
        val intent = Intent(this, crearCotizacionActivity::class.java)
        intent.putExtra("idCotizacion", item.ID.toString())
        startActivity(intent)
    }
}
