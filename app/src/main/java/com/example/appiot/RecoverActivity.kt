package com.example.appiot

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class RecoverActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover)

        auth = FirebaseAuth.getInstance()

        val correo = findViewById<EditText>(R.id.etCorreo)
        val btnRecuperar = findViewById<Button>(R.id.btnRecuperarClave)
        val btnVolver = findViewById<Button>(R.id.btnVolverLoginDesdeRecuperar)

        btnVolver.setOnClickListener { finish() }

        btnRecuperar.setOnClickListener {
            val email = correo.text.toString().trim()

            if (email.isEmpty()) {
                mostrarDialogo("Error", "Por favor ingrese un correo válido.")
                return@setOnClickListener
            }

            auth.fetchSignInMethodsForEmail(email).addOnCompleteListener { task ->
                val existe = task.result?.signInMethods?.isNotEmpty() ?: false

                if (!existe) {
                    mostrarDialogo("Error", "El correo ingresado no está registrado.")
                    return@addOnCompleteListener
                }

                val nuevaClave = (100000..999999).random().toString()
                mostrarDialogo("Nueva contraseña generada",
                    "Tu nueva contraseña es:\n\n$nuevaClave\n\nGuárdala en un lugar seguro.")
            }
        }
    }

    private fun mostrarDialogo(titulo: String, mensaje: String) {
        AlertDialog.Builder(this)
            .setTitle(titulo)
            .setMessage(mensaje)
            .setPositiveButton("Aceptar", null)
            .show()
    }
}
