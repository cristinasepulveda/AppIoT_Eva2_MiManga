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

        Handler(Looper.getMainLooper()).postDelayed({
            mostrarDialogoBluetooth()
        }, 1000)
    }

    private fun mostrarDialogoBluetooth() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Bluetooth")
        builder.setMessage("El estado de Bluetooth ha sido leído correctamente.")
        builder.setPositiveButton("Aceptar") { _, _ ->
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        builder.setCancelable(false)
        builder.show()
    }
}



