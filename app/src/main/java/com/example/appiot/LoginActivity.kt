package com.example.appiot

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.etEmailLogin)
        val clave = findViewById<EditText>(R.id.etClave)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val btnRecuperar = findViewById<Button>(R.id.btnRecuperar)

        btnLogin.setOnClickListener {
            val mail = email.text.toString()
            val pass = clave.text.toString()

            val prefs = getSharedPreferences("AppIoTPrefs", Context.MODE_PRIVATE)
            val savedEmail = prefs.getString("email", null)
            val savedPass = prefs.getString("clave", null)
            val savedName = prefs.getString("nombre", "")

            val builder = AlertDialog.Builder(this)
            if (mail == savedEmail && pass == savedPass) {
                builder.setTitle("Inicio de sesión exitoso")
                builder.setMessage("Bienvenido, $savedName 👋")
            } else {
                builder.setTitle("Error")
                builder.setMessage("Correo o contraseña incorrectos.")
            }
            builder.setPositiveButton("Aceptar", null)
            builder.show()
        }

        btnRegistrar.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnRecuperar.setOnClickListener {
            startActivity(Intent(this, RecoverActivity::class.java))
        }
    }
}

