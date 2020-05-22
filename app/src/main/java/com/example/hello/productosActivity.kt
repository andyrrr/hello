package com.example.hello

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_productos.*

class productosActivity : AppCompatActivity(), OnInventarioItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        //generateDummyList(100)

        if (!Inventario.vacio()) {
            val text: TextView = findViewById<TextView>(R.id.tvInventarioVacio)
            text.setVisibility(View.GONE);


            recyclerView.adapter = adaptadorAInventario(Inventario.listaProductos,this)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.setHasFixedSize(true)
        }
    }

    fun onClickCrearProductoNuevo(v: View) {
        val intent = Intent(this, crearProductoActivity::class.java)
        intent.putExtra("idProducto",0.toString())
        startActivity(intent)
    }



    override fun onProductoClick(item: Producto, position: Int) {

        val intent = Intent(this, crearProductoActivity::class.java)
        intent.putExtra("idProducto",item.ID.toString())
        startActivity(intent)
    }

    override fun onCotizacionClick(item: Cotizacion, position: Int) {
        TODO("Not yet implemented")
    }
}
