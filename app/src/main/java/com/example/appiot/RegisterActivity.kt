package com.example.appiot

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etClave = findViewById<EditText>(R.id.etClave)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        // 🚨 NUEVA LÍNEA: Referencia al botón de Volver al Login 🚨
        val btnVolverLogin = findViewById<Button>(R.id.btnVolverLoginDesdeRegistro)

        btnRegistrar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val clave = etClave.text.toString().trim()

            if (nombre.isEmpty() || email.isEmpty() || clave.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // (Se asume que ya corregiste la validación de 6 caracteres aquí)

            auth.createUserWithEmailAndPassword(email, clave)
                .addOnSuccessListener {
                    // opcional: actualizar displayName, o guardar en Firestore
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }

        // 🚨 NUEVO BLOQUE: Listener para el botón de Volver al Login 🚨
        btnVolverLogin.setOnClickListener {
            // finish() cierra la actividad actual (RegisterActivity) y regresa a la anterior (LoginActivity).
            finish()
        }
    }
}