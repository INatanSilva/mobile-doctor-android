package com.example.mobile_teste.navigation

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mobile_teste.TelaEscolhaPacienteOuDoutor
import com.example.mobile_teste.TelaRegistroPaciente

@Composable
fun AppNavigation(navController: NavController) {
    NavHost(navController = navController, startDestination = "telaEscolha") {
        composable("telaEscolha") { TelaEscolhaPacienteOuDoutor(navController) }
        composable("telaRegistroPaciente") { TelaRegistroPaciente(navController) }
        composable("login") { LoginScreen(navController) }
        // Outras rotas...
    }
}

fun NavHost(
    navController: NavController,
    startDestination: String,
    builder: NavGraphBuilder.() -> Unit
) {
    TODO("Not yet implemented")
}
