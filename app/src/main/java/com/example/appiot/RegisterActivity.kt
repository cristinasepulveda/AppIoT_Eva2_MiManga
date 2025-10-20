package com.example.appiot

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val usuario = findViewById<EditText>(R.id.etNuevoUsuario)
        val clave = findViewById<EditText>(R.id.etNuevaClave)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrarCuenta)
        val btnVolver = findViewById<Button>(R.id.btnVolverLoginDesdeRegistro)
        btnVolver.setOnClickListener {
            finish() // vuelve a la Activity anterior (Login)
        }


        btnRegistrar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Registro exitoso")
            builder.setMessage("Usuario ${usuario.text} registrado correctamente.")
            builder.setPositiveButton("Aceptar", null)
            builder.show()
        }
    }
}
