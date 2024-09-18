package com.example.desafio2restaurante

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class InicioActivity : AppCompatActivity() {

    val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val botonIrAPlatos = findViewById<Button>(R.id.btn_ir_a_platos)
        botonIrAPlatos.setOnClickListener {
            val intent = Intent(this, PlatosActivity::class.java)
            startActivity(intent)
        }

        val btnCerrar = findViewById<Button>(R.id.cerrar)
                btnCerrar.setOnClickListener {
            auth.signOut()
            // Redirigir al usuario a la pantalla de inicio de sesión o donde corresponda
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
