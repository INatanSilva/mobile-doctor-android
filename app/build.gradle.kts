plugins {
    alias(libs.plugins.android.application) // Mantenha isso se estiver usando version catalogs
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services") // Necessário para integrar o Firebase
}

android {
    namespace = "com.example.mobile_teste"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mobile_teste"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation ("com.google.accompanist:accompanist-pager:0.25.1")
    implementation ("androidx.compose.runtime:runtime-saveable:1.7.6")
    implementation ("androidx.compose.animation:animation:1.6.0")
    implementation ("androidx.compose.ui:ui:1.5.0") // ou a versão mais recente
    implementation ("androidx.compose.material3:material3:1.1.0") // para Material3
    implementation ("androidx.compose.foundation:foundation:1.5.0") // ou a versão mais recente
    implementation ("androidx.compose.runtime:runtime:1.5.0") // ou a versão mais recente
    implementation ("com.google.firebase:firebase-database-ktx:20.1.0")  // Firebase Realtime Database
    implementation ("com.google.firebase:firebase-auth-ktx:21.0.0") // Para autenticação
    implementation("com.google.firebase:firebase-firestore")
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("androidx.compose.material:material-icons-extended:1.5.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.animation.core.lint)
    implementation(libs.androidx.espresso.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

}

