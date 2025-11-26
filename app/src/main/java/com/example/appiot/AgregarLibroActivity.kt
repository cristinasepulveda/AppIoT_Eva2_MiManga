package com.example.appiot

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class AgregarLibroActivity : AppCompatActivity() {

    private val db by lazy { FirebaseFirestore.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_libro)

        // 🚨 NUEVA REFERENCIA: Botón Volver 🚨
        val btnVolver = findViewById<Button>(R.id.btnVolverDesdeAgregarLibro)

        val etTitulo = findViewById<EditText>(R.id.etTitulo)
        val etResumen = findViewById<EditText>(R.id.etResumen)
        val etAutor = findViewById<EditText>(R.id.etAutor)
        val etImageUrl = findViewById<EditText>(R.id.etImageUrl)
        val btnCrear = findViewById<Button>(R.id.btnCrear)

        // 🚨 ACCIÓN BOTÓN VOLVER 🚨
        btnVolver.setOnClickListener {
            finish() // Cierra la actividad y regresa a HomeActivity
        }

        // Logica principal de CREAR LIBRO
        btnCrear.setOnClickListener {
            val titulo = etTitulo.text.toString().trim()
            val resumen = etResumen.text.toString().trim()
            val autor = etAutor.text.toString().trim()
            val imagenUrl = etImageUrl.text.toString().trim()

            if (titulo.isEmpty() || resumen.isEmpty() || autor.isEmpty()) {
                Toast.makeText(this, "Completa título, resumen y autor", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (imagenUrl.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa una URL de imagen.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Progress
            val progress = ProgressDialog(this)
            progress.setMessage("Agregando libro...")
            progress.setCancelable(false)
            progress.show()

            // Guardamos en Firestore
            val libro = hashMapOf(
                "titulo" to titulo,
                "resumen" to resumen,
                "autor" to autor,
                "imagenUrl" to imagenUrl
            )

            db.collection("libros").add(libro)
                .addOnSuccessListener {
                    progress.dismiss()
                    Toast.makeText(this, "Libro agregado", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    progress.dismiss()
                    // Asegúrate de que tus reglas de Firestore permitan la escritura aquí
                    Toast.makeText(this, "Error al agregar libro: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }
    }
}