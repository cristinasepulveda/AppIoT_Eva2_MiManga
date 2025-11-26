plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
    id("kotlin-kapt")

}

android {
    namespace = "com.example.appiot"
    compileSdk = 34
    kotlinOptions {
        jvmTarget = "1.8"
    }
    defaultConfig {
        applicationId = "com.example.appiot"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        manifestPlaceholders["appCheckProvider"] = "none"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation("com.google.firebase:firebase-appcheck-playintegrity:18.0.0")
    implementation(platform("com.google.firebase:firebase-bom:33.5.0"))

    // Firebase Auth
    implementation("com.google.firebase:firebase-auth")

    // Google Sign-In
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    // AndroidX
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
// Glide (para cargar imágenes en el RecyclerView)
    implementation("com.github.bumptech.glide:glide:4.16.0")

    kapt("com.github.bumptech.glide:compiler:4.16.0")
// Firebase Storage
    implementation("com.google.firebase:firebase-storage")

    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-firestore-ktx:25.0.0")
    implementation("com.google.firebase:firebase-storage-ktx:21.0.0")
    implementation("com.google.firebase:firebase-auth-ktx:23.0.0")


}
