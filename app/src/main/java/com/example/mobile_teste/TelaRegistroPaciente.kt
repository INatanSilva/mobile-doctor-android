package com.example.mobile_teste

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase
import androidx.compose.ui.Modifier

@Composable
fun TelaRegistroPaciente(navController: NavController) {
    // Estados para armazenar os valores dos campos
    var nome by remember { mutableStateOf(TextFieldValue("")) }
    var apelido by remember { mutableStateOf(TextFieldValue("")) }
    var idade by remember { mutableStateOf(TextFieldValue("")) }
    var dataNascimento by remember { mutableStateOf(TextFieldValue("")) }
    var regiao by remember { mutableStateOf(TextFieldValue("")) }

    // Referência ao Firebase Database
    val database = FirebaseDatabase.getInstance()
    val databaseRef = database.getReference("pacientes")

    // Função para salvar dados no Firebase
    fun savePatientData() {
        val patientId = databaseRef.push().key ?: return // Gera um ID único para o paciente
        val patient = mapOf(
            "nome" to nome.text,
            "apelido" to apelido.text,
            "idade" to idade.text,
            "regiao" to regiao.text
        )
        databaseRef.child(patientId).setValue(patient)
            .addOnSuccessListener {
                Log.d("Firebase", "Dados salvos com sucesso!")
                // Navegar para a próxima tela após salvar
                navController.navigate("proximaTela")
            }
            .addOnFailureListener { exception ->
                Log.e("Firebase", "Erro ao salvar dados: ${exception.message}")
            }
    }

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
                    // Chama a função para salvar os dados
                    savePatientData()
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
