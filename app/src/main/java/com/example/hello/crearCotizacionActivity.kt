package com.example.hello

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_crear_cotizacion.*

class crearCotizacionActivity : AppCompatActivity() {
    val id: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cotizacion)

        val objetoIntent: Intent = intent
        val idCotizacion = objetoIntent.getStringExtra("idCotizacion")

        if (idCotizacion == "Actual") {
            actualizarPlantilla()
        } else if (idCotizacion == "0") {
            Inventario.limpiarCotizacionActual()
        } else {
            val i = Inventario.listaCotizacion.find { it.ID.toString() == idCotizacion }
            if (i != null) {
                Inventario.actualizarCotizacionActual(
                    i.fecha, i.numero, i.caducidad, i.nombre, i.telefono, i.correo
                )
                Inventario.cotizacionActual.ID = i.ID
                Inventario.cotizacionActual.actualizarLista(i.listaProductos)
            }
            actualizarPlantilla()
        }
    }

    fun actualizarPlantilla() {
        etFechaCotizacion.setText(Inventario.cotizacionActual.fecha)
        etNumeroCotizacion.setText(Inventario.cotizacionActual.numero)
        etCaducidadCotizacion.setText(Inventario.cotizacionActual.caducidad)
        etNombreCliente.setText(Inventario.cotizacionActual.nombre)
        etTelefono.setText(Inventario.cotizacionActual.telefono)
        etCorreo.setText(Inventario.cotizacionActual.correo)
        etNumeroCotizacion.setText(Inventario.cotizacionActual.ID.toString())
        if (!Inventario.cotizacionActual.listaProductos.none()) {
            totalProductos.text = Inventario.cotizacionActual.listaProductos.size.toString()
        }
    }

    fun onClickVerProductos(v: View) {
        actualizar()
        val intent = Intent(this, comprarActivity::class.java)
        intent.putExtra("idCotizacion", "Actual")
        startActivity(intent)
    }


    fun onClickCancelar(v: View) {
        //Inventario.limpiarCotizacionActual()
        finish()
    }

    fun onClickEnviar(v: View) {

    }

    fun onClickGuardar(v: View) {
        actualizar()
        Inventario.agregarCotizacion(Inventario.cotizacionActual)
        val intent = Intent(this, cotizacionesActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent)
    }

    fun onClickSeleccionarProductos(v: View) {
        actualizar()
        val intent = Intent(this, comprarActivity::class.java)
        intent.putExtra("idCotizacion", "Nueva")
        startActivity(intent)
    }

    fun actualizar() {
        Inventario.actualizarCotizacionActual(
            etFechaCotizacion.text.toString(),
            etNumeroCotizacion.text.toString(),
            etCaducidadCotizacion.text.toString(),
            etNombreCliente.text.toString(),
            etTelefono.text.toString(),
            etCorreo.text.toString()
        )
    }
}
