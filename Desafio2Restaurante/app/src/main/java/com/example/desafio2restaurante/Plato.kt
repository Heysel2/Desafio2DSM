package com.example.desafio2restaurante

class Plato {
    fun key(key: String?) {
    }
    var precio: Double? = null
    var nombre: String? = null
    var key: String? = null
    var per: MutableMap<String, Boolean> = HashMap()
    constructor() {}
    constructor(precio: Double?, nombre: String?) {
        this.precio = precio
        this.nombre = nombre
    }
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "precio" to precio,
            "nombre" to nombre,
            "key" to key,
            "per" to per
        )
    }
}