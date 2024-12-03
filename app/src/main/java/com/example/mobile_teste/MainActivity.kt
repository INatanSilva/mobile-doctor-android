package com.example.mobile_teste

import LoginScreen
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.*

import com.example.mobile_teste.ui.theme.MobiletesteTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobiletesteTheme {
                // Configurando o NavController
                val navController = rememberNavController()

                // Criando o NavHost para navegação entre as telas
                NavHost(navController = navController, startDestination = "telaEscolhaPacienteOuDoutor") {
                    // Tela de escolha de Paciente ou Doutor
                    composable("telaEscolhaPacienteOuDoutor") {
                        TelaEscolhaPacienteOuDoutor(navController = navController)
                    }

                    // Tela de login
                    composable("login") {
                        LoginScreen(navController) // A tela de login que você já criou
                    }

                    // Tela de registro de paciente
                    composable("registroPaciente") {
                        TelaRegistroPaciente(navController = navController)
                    }
                }
            }
        }
    }
}
