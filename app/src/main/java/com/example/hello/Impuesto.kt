package com.example.hello

interface Impuesto {

    fun calcularImpuesto(precio: Double): Double {
        return precio * 0.13
    }

}