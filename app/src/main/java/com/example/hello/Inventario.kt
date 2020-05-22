package com.example.hello

object Inventario {

    var listaProductos: MutableList<Producto> = mutableListOf()
    var listaCotizacion: MutableList<Cotizacion> = mutableListOf()

    //Cotizacion YA TIENE ID CAMBIARRRR
    var cotizacionActual: Cotizacion = Cotizacion("", "", "", "", "", "", mutableListOf(), 1)

    var IDCotizacion = 48
    var IDProducto = 78

    fun agregarProducto(articulo: String, descripcion: String, precioUnitario: Double, id: Int) {
        if (id == 0) {
            val p = Producto(articulo, descripcion, precioUnitario, IDProducto)
            IDProducto += 60
            listaProductos.add(0, p)
        } else {
            listaProductos.find { it.ID == id }?.actualizar(articulo, descripcion, precioUnitario)
        }
    }


    //ARREGLAR ESTO PORQUE CRAP
    fun APair(): MutableList<Pair<Int, String>> {
        val lista: MutableList<Pair<Int, String>> = mutableListOf()
        for (producto in listaProductos) {
            val i =
                cotizacionActual.listaProductos.indexOfFirst { it.second == producto.ID.toString() }
            if (i != -1) {
                lista.add(0, Pair(cotizacionActual.listaProductos[i].first, producto.ID.toString()))
            } else {
                lista.add(0, Pair(0, producto.ID.toString()))
            }
        }
        return lista
    }


    fun actualizarCotizacionActual(
        fecha: String,
        numero: String,
        caducidad: String,
        nombre: String,
        telefono: String,
        correo: String
    ) {
        cotizacionActual.actualizar(
            fecha,
            numero,
            caducidad,
            nombre,
            telefono,
            correo
        )
    }

    fun limpiarCotizacionActual(){
        cotizacionActual.ID = 1
        cotizacionActual.fecha = ""
        cotizacionActual.numero = ""
        cotizacionActual.caducidad = ""
        cotizacionActual.nombre = ""
        cotizacionActual.telefono = ""
        cotizacionActual.correo= ""
        cotizacionActual.listaProductos = mutableListOf()
    }

    fun agregarCotizacion(c: Cotizacion){
        if (c.ID == 1) {
            c.ID = IDCotizacion
            IDCotizacion += 60
            listaCotizacion.add(0, c)
        } else {
            val cot = listaCotizacion.find { it.ID == c.ID }
            if (cot != null) {
                cot.actualizar(c.fecha, c.numero, c.caducidad, c.nombre, c.telefono, c.correo)
                cot.actualizarLista(c.listaProductos)
            }
        }
        cotizacionActual  = Cotizacion("", "", "", "", "", "", mutableListOf(), 1)

        println(listaCotizacion)
    }

    fun size(): Int {
        return listaProductos.size
    }

    fun get(i: Int): Producto {
        return listaProductos.elementAt(i)
    }

    fun vacio(): Boolean {
        return listaProductos.none()
    }
}