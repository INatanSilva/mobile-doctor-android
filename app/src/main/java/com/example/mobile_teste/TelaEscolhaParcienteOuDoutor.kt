package com.example.mobile_teste

import android.util.Log
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
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp), // Espaçamento menor entre os botões
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    escolha = "Paciente"
                    Log.d("EscolhaUsuario", "Escolha: $escolha")
                    navController.navigate("registroPaciente")
                },
                modifier = Modifier
                    .width(200.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(text = "Paciente", color = Color.White)
            }

            Button(
                onClick = {
                    escolha = "Doutor"
                    Log.d("EscolhaUsuario", "Escolha: $escolha")
                    navController.navigate("telaRegistroDoutor")
                },
                modifier = Modifier
                    .width(200.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(text = "Doutor", color = Color.White)
            }
        }
    }
}
