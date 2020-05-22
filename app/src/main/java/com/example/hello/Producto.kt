package com.example.hello

class Producto(
    var articulo: String,
    var descripcion: String,
    var precioUnitario: Double,
    var ID: Int
) :
    Impuesto {
    var impuesto: Double = calcularImpuesto(precioUnitario)

    fun actualizar(Narticulo: String, Ndescripcion: String, NprecioUnitario: Double) {
        articulo = Narticulo
        descripcion = Ndescripcion
        precioUnitario = NprecioUnitario
        impuesto = calcularImpuesto(precioUnitario)
    }

    override fun toString(): String {
        return "Producto(articulo='$articulo', descripcion='$descripcion', precioUnitario=$precioUnitario, ID=$ID, impuesto=$impuesto)"
    }

}