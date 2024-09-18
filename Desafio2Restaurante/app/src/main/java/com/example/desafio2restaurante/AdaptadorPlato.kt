package com.example.desafio2restaurante

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.example.desafio2restaurante.Plato

class AdaptadorPlato(private val context: Activity, var platos: List<Plato>) :
    ArrayAdapter<Plato?>(context, R.layout.plato_layout, platos) {

    private val seleccionados = mutableSetOf<Plato>()

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val layoutInflater = context.layoutInflater
        val rowView = view ?: layoutInflater.inflate(R.layout.plato_layout, null)

        val plato = platos[position]
        val tvNombre = rowView.findViewById<TextView>(R.id.tvNombre)
        val tvPrecio = rowView.findViewById<TextView>(R.id.tvPrecio)
        val cbSeleccionar = rowView.findViewById<CheckBox>(R.id.cbSeleccionar)

        tvNombre.text = "${plato.nombre}"
        tvPrecio.text = "Precio : ${plato.precio}"

        cbSeleccionar.isChecked = seleccionados.contains(plato)
        cbSeleccionar.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                seleccionados.add(plato)
            } else {
                seleccionados.remove(plato)
            }
            (context as PlatosActivity).actualizarTotal()
        }

        return rowView
    }

    fun getSeleccionados(): Set<Plato> {
        return seleccionados
    }
}
