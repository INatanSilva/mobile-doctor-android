package com.example.mobile_teste

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TelaEscolhaPacienteOuDoutor(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navController.navigate("login") }, // Navega para a tela de login
            modifier = Modifier
                .width(200.dp) // Defina uma largura fixa para o botão
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
            onClick = { navController.navigate("login") }, // Navega para a tela de login
            modifier = Modifier
                .width(200.dp) // Defina uma largura fixa para o botão
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
