package com.example.mobile_teste

import LoginScreen
import TelaInicial
import TelaRegistroPaciente
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
import kotlinx.coroutines.delay
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MobiletesteTheme {
                // Configurando o NavController
                val navController = rememberNavController()

                // Estado para controlar o carregamento
                var isLoading by remember { mutableStateOf(true) }
                var startDestination by remember { mutableStateOf("login") }  // Tela inicial

                // Verificando o login do usuário
                LaunchedEffect(Unit) {
                    // Simula um carregamento de 3 segundos
                    delay(3000)  // Simula o delay de carregamento

                    // Verifica se o usuário está logado
                    val auth = FirebaseAuth.getInstance()
                    val currentUser = auth.currentUser
                    startDestination = if (currentUser != null) {
                        "telaInicial"  // Se estiver logado, redireciona para a tela inicial
                    } else {
                        "login"  // Caso contrário, para a tela de login
                    }

                    isLoading = false  // Carregamento concluído
                }

                // Exibir ícone de carregamento ou conteúdo baseado no estado
                if (isLoading) {
                    LoadingScreen()
                } else {
                    // Tela de navegação normal após o carregamento
                    NavHost(
                        navController = navController,
                        startDestination = startDestination
                    ) {
                        // Tela de escolha de Paciente ou Doutor
                        composable("telaEscolhaPacienteOuDoutor") {
                            TelaEscolhaPacienteOuDoutor(navController = navController)
                        }

                        // Tela de login
                        composable("login") {
                            LoginScreen(
                                navController,
                                onLogin = { name, email ->
                                    navController.navigate("telaInicial") {
                                        // Passa o email para a tela inicial
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }

                        // Tela de registro de paciente
                        composable("registroPaciente") {
                            TelaRegistroPaciente(navController = navController)
                        }

                        // Tela inicial
                        composable("telaInicial") {
                            val userEmail = ""
                            val userName = ""
                            TelaInicial(
                                userEmail = userEmail,
                                userName = userName
                            )
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
