package com.example.appiot

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usuario = findViewById<EditText>(R.id.etUsuario)
        val clave = findViewById<EditText>(R.id.etClave)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val btnRecuperar = findViewById<Button>(R.id.btnRecuperar)

        // --- Botón Iniciar Sesión ---
        btnLogin.setOnClickListener {
            val user = usuario.text.toString()
            val pass = clave.text.toString()

            val builder = AlertDialog.Builder(this)
            if (user == "admin" && pass == "1234") {
                builder.setTitle("Inicio de sesión correcto")
                builder.setMessage("Bienvenido $user 👋")
            } else {
                builder.setTitle("Error")
                builder.setMessage("Usuario o contraseña incorrectos.")
            }
            builder.setPositiveButton("Aceptar", null)
            builder.show()
        }

        // --- Botón Registrar ---
        btnRegistrar.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // --- Botón Recuperar clave ---
        btnRecuperar.setOnClickListener {
            val intent = Intent(this, RecoverActivity::class.java)
            startActivity(intent)
        }
    }
}
