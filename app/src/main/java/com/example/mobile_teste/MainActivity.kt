package com.example.mobile_teste

import LoginScreen
import TelaInicial
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.mobile_teste.ui.theme.MobiletesteTheme
import TelaInicial

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MobiletesteTheme {
                // Configurando o NavController
                val navController = rememberNavController()

                // Estado para controlar o carregamento
                var isLoading by remember { mutableStateOf(true) }

                // Simula um carregamento de 3 segundos
                LaunchedEffect(Unit) {
                    kotlinx.coroutines.delay(3000)  // Simula o delay de carregamento
                    isLoading = false
                }

                // Exibir ícone de carregamento ou conteúdo baseado no estado
                if (isLoading) {
                    // Tela de carregamento
                    LoadingScreen()
                } else {
                    // Tela de navegação normal após o carregamento
                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    ) {
                        // Tela de escolha de Paciente ou Doutor
                        composable("telaEscolhaPacienteOuDoutor") {
                            TelaEscolhaPacienteOuDoutor(navController = navController)
                        }

                        // Tela de login
                        composable("login") {
                            LoginScreen(navController) // Corrigido o nome da tela LoginScreen
                        }

                        // Tela de registro de paciente
                        composable("registroPaciente") {
                            TelaRegistroPaciente(navController = navController)
                        }

                        // Tela de registro do doutor
                        composable("telaRegistroDoutor") {
                            TelaRegistroDoutor(navController = navController)
                        }

                        composable("telaInicial") {
                            TelaInicial(navController = navController)
                        }
                    }
                }
            }
        }
    }

    // Função para exibir a tela de carregamento
    @Composable
    fun LoadingScreen() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()  // Ícone de carregamento
        }
    }
}
