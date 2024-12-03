// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

buildscript {
    repositories {
        google() // Certifique-se de que o repositório do Google está aqui
        mavenCentral()
    }

    dependencies {
        // Outras dependências
        classpath ("com.google.gms:google-services:4.3.15") // A versão mais recente do plugin
    }
}
