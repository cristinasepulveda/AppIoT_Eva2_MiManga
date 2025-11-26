package com.example.appiot

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Inicializar Firebase
        FirebaseApp.initializeApp(this)

        // Activar App Check con Play Integrity
        val appCheck = FirebaseAppCheck.getInstance()
        appCheck.installAppCheckProviderFactory(
            PlayIntegrityAppCheckProviderFactory.getInstance()
        )
    }
}
