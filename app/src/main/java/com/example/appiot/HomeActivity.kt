package com.example.appiot

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appiot.adapter.LibroAdapter
import com.example.appiot.model.Libro
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class HomeActivity : AppCompatActivity() {

    private val db by lazy { FirebaseFirestore.getInstance() }
    private val auth by lazy { FirebaseAuth.getInstance() }
    private lateinit var listener: ListenerRegistration
    private val listaLibros = ArrayList<Libro>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val recycler = findViewById<RecyclerView>(R.id.recyclerLibros)
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)
        val btnCerrar = findViewById<Button>(R.id.btnCerrarSesion)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = LibroAdapter(listaLibros) { libro ->
            val i = Intent(this, VerLibroActivity::class.java)
            i.putExtra("titulo", libro.titulo)
            i.putExtra("resumen", libro.resumen)
            i.putExtra("autor", libro.autor)
            i.putExtra("imagenUrl", libro.imagenUrl)
            startActivity(i)
        }

        btnAgregar.setOnClickListener {
            startActivity(Intent(this, AgregarLibroActivity::class.java))
        }

        btnCerrar.setOnClickListener {
            auth.signOut()
            finish()
        }

        // Listener en tiempo real
        listener = db.collection("libros").addSnapshotListener { snapshot, error ->
            if (error != null) return@addSnapshotListener

            listaLibros.clear()
            snapshot?.documents?.forEach { doc ->
                val n = doc.toObject(Libro::class.java)
                n?.let { listaLibros.add(it) }
            }
            recycler.adapter?.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // remover listener si fue inicializado
        try { listener.remove() } catch (_: Exception) {}
    }
}
