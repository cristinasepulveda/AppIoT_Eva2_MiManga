package com.example.appiot

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class RecoverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover)

        val correo = findViewById<EditText>(R.id.etCorreo)
        val btnRecuperar = findViewById<Button>(R.id.btnRecuperarClave)
        val btnVolver = findViewById<Button>(R.id.btnVolverLoginDesdeRecuperar)
        btnVolver.setOnClickListener {
            finish() // regresa a Login
        }


        btnRecuperar.setOnClickListener {
            val email = correo.text.toString()
            val builder = AlertDialog.Builder(this)
            if (email.isNotEmpty()) {
                builder.setTitle("Solicitud enviada")
                builder.setMessage("Se ha enviado un correo a $email con instrucciones para restablecer la contraseña.")
            } else {
                builder.setTitle("Error")
                builder.setMessage("Por favor, ingrese un correo válido.")
            }
            builder.setPositiveButton("Aceptar", null)
            builder.show()
        }
    }
}
