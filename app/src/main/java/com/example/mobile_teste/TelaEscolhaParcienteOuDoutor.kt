package com.example.mobile_teste

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TelaEscolhaPacienteOuDoutor(navController: NavController) {
    // Variável para armazenar a escolha do usuário
    var escolha by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                escolha = "Paciente" // Atualiza a variável com a escolha
                Log.d("EscolhaUsuario", "Escolha: $escolha") // Registra no console
                navController.navigate("registroPaciente") // Navega para a TelaRegistroPaciente
            },
            modifier = Modifier
                .width(200.dp)
                .padding(vertical = 15.dp)
                .border(2.dp, Color(0xFF8B9F8E), RoundedCornerShape(20.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB8D8B7), // Cor verde mais suave no fundo do botão
                contentColor = Color.Black // Texto preto
            )
        ) {
            Text("Paciente")
        }

        Button(
            onClick = {
                escolha = "Doutor" // Atualiza a variável com a escolha
                Log.d("EscolhaUsuario", "Escolha: $escolha") // Registra no console
                navController.navigate("login") // Navega para a tela de login
            },
            modifier = Modifier
                .width(200.dp)
                .padding(vertical = 8.dp)
                .border(2.dp, Color(0xFF8B9F8E), RoundedCornerShape(20.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB8D8B7), // Cor verde mais suave no fundo do botão
                contentColor = Color.Black // Texto preto
            )
        ) {
            Text("Doutor")
        }
    }
}
