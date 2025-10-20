package com.example.appiot

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Simulación de lectura del estado Bluetooth
        Handler(Looper.getMainLooper()).postDelayed({
            mostrarDialogoBluetooth()
        }, 1000) // espera 1 segundo antes del mensaje
    }

    private fun mostrarDialogoBluetooth() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Bluetooth")
        builder.setMessage("El estado de Bluetooth ha sido leído correctamente.")
        builder.setPositiveButton("Aceptar") { _, _ ->
            // Luego de aceptar, pasa al Login
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        builder.setCancelable(false) // evita cerrar el diálogo accidentalmente
        builder.show()
    }
}
