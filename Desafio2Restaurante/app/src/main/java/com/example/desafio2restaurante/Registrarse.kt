package com.example.desafio2restaurante

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Registrarse : AppCompatActivity() {

    //referencia firebase
    private lateinit var auth: FirebaseAuth

    private lateinit var buttonRegistrarse: Button
    private lateinit var textViewLogin : TextView
    private lateinit var authStateListener:  FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)

        //iniciar firebase
        auth = FirebaseAuth.getInstance()

        buttonRegistrarse = findViewById<Button>(R.id.btnregistrarse)
        buttonRegistrarse.setOnClickListener {
            val email = findViewById<EditText>(R.id.txtEmail).text.toString()
            val password = findViewById<EditText>(R.id.txtPsw).text.toString()
            this.registrate(email, password)
        }

        textViewLogin = findViewById<TextView>(R.id.textLogin)
        textViewLogin.setOnClickListener {
            this.goToLogin()
        }

        this.checkUser()
    }

    override fun onResume(){
        super.onResume()
        auth.addAuthStateListener(authStateListener)
    }

    override fun onPause(){
        super.onPause()
        auth.removeAuthStateListener(authStateListener)
    }

    private fun checkUser(){
        authStateListener = FirebaseAuth.AuthStateListener{ auth ->
            if(auth.currentUser != null){
                val intent = Intent(this, InicioActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun registrate(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{task ->
        if (task.isSuccessful){
            val intent =  Intent(this, InicioActivity::class.java)
            startActivity(intent)
            finish()
        }
        }.addOnFailureListener {exception ->
            Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun goToLogin(){
        val intent = Intent (this, LoginActivity::class.java)
        startActivity(intent)
    }
        }
