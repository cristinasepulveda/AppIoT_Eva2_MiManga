package com.example.appiot.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appiot.R
import com.example.appiot.model.Libro

class LibroAdapter(
    private val listaLibros: ArrayList<Libro>,
    private val onClick: (Libro) -> Unit
) : RecyclerView.Adapter<LibroAdapter.LibroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_libro, parent, false)
        return LibroViewHolder(view)
    }

    override fun getItemCount(): Int = listaLibros.size

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        holder.bind(listaLibros[position])
    }

    inner class LibroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // 🚨 IDs ACTUALIZADOS: Reintroduciendo itemImagen y itemAutor 🚨
        private val titulo: TextView = itemView.findViewById(R.id.itemTitulo)
        private val resumen: TextView = itemView.findViewById(R.id.itemResumen)
        private val autor: TextView = itemView.findViewById(R.id.itemAutor) // Autor de nuevo
        private val imagen: ImageView = itemView.findViewById(R.id.itemImagen) // Imagen de nuevo

        fun bind(libro: Libro) {
            titulo.text = libro.titulo
            resumen.text = libro.resumen
            autor.text = libro.autor // Mostrar el autor

            // Lógica de carga de imagen con Glide
            if (libro.imagenUrl.isNotEmpty() && libro.imagenUrl != "null") {
                Glide.with(itemView.context)
                    .load(libro.imagenUrl)
                    .into(imagen)
            } else {
                // Si la URL está vacía, limpia la imagen y establece un fondo gris
                imagen.setImageDrawable(null)
                imagen.setBackgroundColor(Color.parseColor("#DDDDDD"))
            }

            itemView.setOnClickListener { onClick(libro) }
        }
    }
}