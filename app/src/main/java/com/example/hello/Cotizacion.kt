package com.example.hello

class Cotizacion(
    var fecha: String,
    var numero: String,
    var caducidad: String,
    var nombre: String,
    var telefono: String,
    var correo: String,
    var listaProductos: MutableList<Pair<Int, String>>,
    var ID: Int
) {
    var isActual = false

    fun actualizar(
        Nfecha: String,
        Nnumero: String,
        Ncaducidad: String,
        Nnombre: String,
        Ntelefono: String,
        Ncorreo: String
    ) {
        fecha = Nfecha
        numero = Nnumero
        caducidad = Ncaducidad
        nombre = Nnombre
        telefono = Ntelefono
        correo = Ncorreo
    }

    fun actualizarLista(NlistaP: MutableList<Pair<Int, String>>) {
        var i: Int
        for (p in NlistaP) {
            i = listaProductos.indexOfFirst { it.second == p.second }
            //si lo encuentra, lo reemplaza
            if (i != -1) {
                if (p.first == 0) {
                    listaProductos.removeAt(i)
                } else {
                    listaProductos[i] = p
                }
            } else {
                if (p.first!=0) {
                    listaProductos.add(p)
                }
            }
            println("----------------------")
        }
    }

    override fun toString(): String {
        return "Cotizacion(fecha='$fecha', numero=$numero, caducidad='$caducidad', nombre='$nombre', telefono=$telefono, correo='$correo', listaProductos=$listaProductos, ID=$ID)"
    }
}


