package com.example.hello

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_crear_producto.*

class crearProductoActivity : AppCompatActivity() {
    var idProducto: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_producto)
        val objetoIntent: Intent  = intent
        idProducto = objetoIntent.getStringExtra("idProducto").toInt()

        if (idProducto.toInt() != 0){
            var i = Inventario.listaProductos.indexOfFirst { it.ID == idProducto }
            etArticulo.setText(Inventario.listaProductos[i].articulo)
            etDescipcion.setText(Inventario.listaProductos[i].descripcion)
            etPrecioUnitario.setText(Inventario.listaProductos[i].precioUnitario.toString())
            button2.setText("Terminar de editar")
        }
    }

    fun onClickCancelar(v: View){
        finish()
    }

    fun onClickAgregar(v: View) {

        val articulo: String = findViewById<EditText>(R.id.etArticulo).text.trim().toString()
        val descipcion: String = findViewById<EditText>(R.id.etDescipcion).text.trim().toString()
        val precio: String = findViewById<EditText>(R.id.etPrecioUnitario).text.trim().toString()

        Inventario.agregarProducto(articulo, descipcion, precio.toDouble(), idProducto)

        val intent = Intent(this, productosActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("Desde", "Main")
        startActivity(intent)
    }
}
