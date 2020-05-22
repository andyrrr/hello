package com.example.hello

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_comprar.*

class comprarActivity : AppCompatActivity(), OnCantidadItemClickListener {
    var carrito = mutableListOf<Pair<Int, String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comprar)

        val objetoIntent: Intent = intent
        val idCotizacion = objetoIntent.getStringExtra("idCotizacion")


        if (idCotizacion == "Nueva") {
            recyclerViewComprar.adapter = adaptadorAComprar(Inventario.APair(), this)
            if (!Inventario.vacio()) {
                tvProductos.visibility = View.GONE
            }

        } else {
            carrito = Inventario.cotizacionActual.listaProductos.toMutableList()
            recyclerViewComprar.adapter =
                adaptadorAComprar(Inventario.cotizacionActual.listaProductos, this)
            if (!carrito.none()) {
                tvProductos.visibility = View.GONE
            }
        }

        recyclerViewComprar.layoutManager = LinearLayoutManager(this)
        recyclerViewComprar.setHasFixedSize(true)
        println(carrito)
    }

    fun onClickAñadir(v: View) {
        println(carrito.toString())
        Inventario.cotizacionActual.actualizarLista(carrito)

        val intent = Intent(this, crearCotizacionActivity::class.java)
        intent.putExtra("idCotizacion", "Actual")
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent)
    }

    //en estos se pueden pasar el adapter position de la lista, pero es mejor que solo lo busque una vez

    override fun onMasClick(textView: TextView, cantidad: Int, id: String) {
        var num = cantidad + 1
        textView.text = num.toString()
        verificar(num, id)
    }

    override fun onMenosClick(textView: TextView, cantidad: Int, id: String) {
        var num = 0
        if (cantidad != 0) {
            num = cantidad - 1
            textView.text = num.toString()
        }
        verificar(num, id)
    }

    fun verificar(num: Int, id: String) {
        //si encuentra el id retorna el index sino, un -1
        val i = carrito.indexOfFirst { it.second == id }
        //si lo encuentra, lo reemplaza
        if (i != -1) {
            carrito[i] = Pair(num, id)
            println(carrito)
        } else {
            carrito.add(Pair(num, id))
        }
    }
}
