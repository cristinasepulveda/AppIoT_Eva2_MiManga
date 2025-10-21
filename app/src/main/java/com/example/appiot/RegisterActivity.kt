package com.example.appiot

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val nombre = findViewById<EditText>(R.id.etNombre)
        val email = findViewById<EditText>(R.id.etEmail)
        val clave = findViewById<EditText>(R.id.etNuevaClave)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrarCuenta)
        val btnVolver = findViewById<Button>(R.id.btnVolverLoginDesdeRegistro)

        btnRegistrar.setOnClickListener {
            val name = nombre.text.toString()
            val mail = email.text.toString()
            val pass = clave.text.toString()

            if (name.isNotEmpty() && mail.isNotEmpty() && pass.isNotEmpty()) {
                val prefs = getSharedPreferences("AppIoTPrefs", Context.MODE_PRIVATE)
                val editor = prefs.edit()
                editor.putString("nombre", name)
                editor.putString("email", mail)
                editor.putString("clave", pass)
                editor.apply()

                val builder = AlertDialog.Builder(this)
                builder.setTitle("Registro exitoso")
                builder.setMessage("Cuenta creada para: $name\nCorreo: $mail")
                builder.setPositiveButton("Aceptar") { _, _ -> finish() }
                builder.show()
            } else {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Error")
                builder.setMessage("Debes completar todos los campos.")
                builder.setPositiveButton("Aceptar", null)
                builder.show()
            }
        }

        btnVolver.setOnClickListener { finish() }
    }
}
