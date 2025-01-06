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
    val userName = rememberSaveable { mutableStateOf("") }
    val userEmail = rememberSaveable { mutableStateOf("") }

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController) { name, email ->
                userName.value = name
                userEmail.value = email
                navController.navigate("telaInicial")
            }
        }

        composable("telaInicial") {
            TelaInicial(navController, userName.value, userEmail.value)
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
