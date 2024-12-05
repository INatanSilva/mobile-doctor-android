package com.example.mobile_teste.navigation

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mobile_teste.*

@Composable
fun AppNavigation(navController: NavController) {
    NavHost(navController = navController, startDestination = "login") {
        // Tela de login
        composable("login") {
            LoginScreen(navController)
        }

        // Tela inicial para escolha de paciente ou doutor
        composable("telaEscolha") {
            TelaEscolhaPacienteOuDoutor(navController)
        }

        // Tela de registro de paciente
        composable("telaRegistroPaciente") {
            TelaRegistroPaciente(navController)
        }

        // Tela de registro de doutor
        composable("telaRegistroDoutor") {
            TelaRegistroDoutor(navController)
        }

        // Outras rotas podem ser adicionadas aqui futuramente
    }
}

fun NavHost(
    navController: NavController,
    startDestination: String,
    builder: NavGraphBuilder.() -> Unit
) {


}

@Composable
fun LoginTela(navController: NavController) {
    TODO("Not yet implemented")
}