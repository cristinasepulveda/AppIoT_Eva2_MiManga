package com.example.appiot

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import androidx.activity.result.contract.ActivityResultContracts

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleClient: GoogleSignInClient

    // Nuevo launcher para Google Sign-In
    private val googleLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)

            auth.signInWithCredential(credential).addOnCompleteListener {
                if (it.isSuccessful) {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                } else {
                    AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage("No se pudo iniciar sesión con Google.")
                        .setPositiveButton("Aceptar", null)
                        .show()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("Hubo un problema con Google Sign-In.")
                .setPositiveButton("Aceptar", null)
                .show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        // Configuración de Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // debe existir en strings.xml
            .requestEmail()
            .build()

        googleClient = GoogleSignIn.getClient(this, gso)

        // Referencias UI
        val email = findViewById<EditText>(R.id.etEmailLogin)
        val clave = findViewById<EditText>(R.id.etClave)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val btnRecuperar = findViewById<Button>(R.id.btnRecuperar)
        val btnGoogle = findViewById<SignInButton>(R.id.btnGoogle)

        // Login normal email/password (AHORA USA FIREBASE AUTH)
        btnLogin.setOnClickListener {
            val mail = email.text.toString().trim()
            val pass = clave.text.toString().trim()

            if (mail.isEmpty() || pass.isEmpty()) {
                AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Por favor, ingresa correo y contraseña.")
                    .setPositiveButton("Aceptar", null)
                    .show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(mail, pass)
                .addOnSuccessListener {
                    // Inicio de sesión exitoso
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
                .addOnFailureListener {
                    // Fallo en el inicio de sesión
                    AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage("Correo o contraseña incorrectos.")
                        .setPositiveButton("Aceptar", null)
                        .show()
                }
        }

        btnRegistrar.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnRecuperar.setOnClickListener {
            startActivity(Intent(this, RecoverActivity::class.java))
        }

        // Google Sign-In con launcher moderno
        btnGoogle.setOnClickListener {
            googleLauncher.launch(googleClient.signInIntent)
        }
    }
}