package com.example.desafio2restaurante

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class PlatosActivity : AppCompatActivity() {

    private lateinit var listaPlatos: ListView
    private lateinit var adapter: AdaptadorPlato
    private lateinit var txtTotal: TextView
    private var platos: MutableList<Plato> = mutableListOf()

    private val consultaOrdenada: Query = refPersonas.orderByChild("nombre")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_platos)
        inicializar()


        val btnhistorial = findViewById<Button>(R.id.historial)
        btnhistorial.setOnClickListener {
            val intent = Intent(this, HistorialActivity::class.java)
            startActivity(intent)
        }

    }

    private fun inicializar() {
        listaPlatos = findViewById(R.id.ListaPlatos)
        txtTotal = findViewById(R.id.txtTotal)

        adapter = AdaptadorPlato(this, platos)
        listaPlatos.adapter = adapter

        consultaOrdenada.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                platos.clear()
                for (dato in dataSnapshot.children) {
                    val plato: Plato? = dato.getValue(Plato::class.java)
                    plato?.key = dato.key
                    plato?.let { platos.add(it) }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    fun actualizarTotal() {
        val seleccionados = adapter.getSeleccionados()
        val total = seleccionados.sumOf { it.precio ?: 0.0 }
        txtTotal.text = "Total: $total"
    }


    companion object {
        private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        private val refPersonas: DatabaseReference = database.getReference("personas")
    }
}
