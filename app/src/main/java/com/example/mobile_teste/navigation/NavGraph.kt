package com.example.mobile_teste.navigation

import LoginScreen
import TelaInicial
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mobile_teste.TelaEscolhaPacienteOuDoutor
import com.example.mobile_teste.TelaRegistroDoutor
import com.example.mobile_teste.TelaRegistroPaciente

@Composable
fun AppNavigation(navController: NavHostController) {
    // Usando 'rememberSaveable' para lembrar os dados entre recomposições
    val userName = rememberSaveable { mutableStateOf("") }
    val userEmail = rememberSaveable { mutableStateOf("") }

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController) { name, email ->
                userName.value = name
                userEmail.value = email as String
                navController.navigate("telaInicial") {
                    // Garantir que o parâmetro seja passado ao navegar
                    launchSingleTop = true
                }
            }
        }

        composable("telaInicial") {
            // Passando o valor de 'userName' e 'userEmail' diretamente para a TelaInicial
            TelaInicial(
                userName = userName.value,
                userEmail = userEmail.value
            )
        }

        composable("telaEscolha") {
            TelaEscolhaPacienteOuDoutor(navController)
        }

        composable("telaRegistroPaciente") {
            TelaRegistroPaciente(navController)
        }

        composable("telaRegistroDoutor") {
            TelaRegistroDoutor(navController)
        }
    }
}
