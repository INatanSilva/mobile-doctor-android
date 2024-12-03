package com.example.mobile_teste

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TelaRegistroPaciente(navController: NavController) {
    // Estados para armazenar os valores dos campos
    var nome by remember { mutableStateOf(TextFieldValue("")) }
    var apelido by remember { mutableStateOf(TextFieldValue("")) }
    var idade by remember { mutableStateOf(TextFieldValue("")) }
    var dataNascimento by remember { mutableStateOf(TextFieldValue("")) }
    var regiao by remember { mutableStateOf(TextFieldValue("")) }

    // Tela principal com fundo amarelo claro
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFEC)), // Fundo da tela
        contentAlignment = Alignment.Center
    ) {
        // Coluna central com fundo branco e borda verde suave
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f) // 90% da largura da tela
                .border(2.dp, Color(0xFFB8D8B7), RoundedCornerShape(16.dp)) // Borda verde suave
                .padding(16.dp)
                .background(Color(0xFFFFFFFF)), // Fundo branco
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título
            Text(
                text = "Registro de Paciente",
                color = Color(0xFF3D7B31), // Verde escuro
                fontSize = 24.sp
            )

            // Campo: Nome
            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome", color = Color(0xFF3D7B31)) },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo: Apelido
            OutlinedTextField(
                value = apelido,
                onValueChange = { apelido = it },
                label = { Text("Apelido", color = Color(0xFF3D7B31)) },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo: Idade
            OutlinedTextField(
                value = idade,
                onValueChange = { idade = it },
                label = { Text("Idade", color = Color(0xFF3D7B31)) },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo: Data de Nascimento
            OutlinedTextField(
                value = dataNascimento,
                onValueChange = { dataNascimento = it },
                label = { Text("Data de Nascimento", color = Color(0xFF3D7B31)) },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo: Região
            OutlinedTextField(
                value = regiao,
                onValueChange = { regiao = it },
                label = { Text("Região", color = Color(0xFF3D7B31)) },
                modifier = Modifier.fillMaxWidth()
            )

            // Botão: Registrar
            Button(
                onClick = {
                    // Ação ao clicar no botão
                    navController.navigate("proximaTela")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9EBD8E), // Fundo verde suave
                    contentColor = Color.White // Texto branco
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Registrar", fontSize = 16.sp)
            }
        }
    }
}
