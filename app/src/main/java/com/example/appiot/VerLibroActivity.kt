package com.example.appiot

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button // Importación necesaria para el botón
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import android.graphics.Color // Importación necesaria para usar Color

class VerLibroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_libro)

        // 🚨 NUEVA REFERENCIA: Botón Volver 🚨
        val btnVolver = findViewById<Button>(R.id.btnVolverDesdeVerLibro)

        val img = findViewById<ImageView>(R.id.imgLibro)
        val txtTitulo = findViewById<TextView>(R.id.txtTitulo)
        val txtAutor = findViewById<TextView>(R.id.txtAutor)
        val txtResumen = findViewById<TextView>(R.id.txtResumen)

        // 🚨 ACCIÓN BOTÓN VOLVER 🚨
        btnVolver.setOnClickListener {
            finish() // Cierra la actividad y regresa
        }

        txtTitulo.text = intent.getStringExtra("titulo") ?: ""
        txtAutor.text = "Por: ${intent.getStringExtra("autor") ?: ""}"
        txtResumen.text = intent.getStringExtra("resumen") ?: ""

        val url = intent.getStringExtra("imagenUrl") ?: ""
        if (url.isNotEmpty()) {
            Glide.with(this).load(url).into(img)
        } else {
            // Solución: Usar un color de fondo en lugar del recurso placeholder eliminado
            img.setImageDrawable(null)
            img.setBackgroundColor(Color.parseColor("#CCCCCC")) // Gris claro
        }
    }
}