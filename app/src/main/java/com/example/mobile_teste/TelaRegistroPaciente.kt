package com.example.mobile_teste

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase
import androidx.compose.ui.Modifier

@Composable
fun TelaRegistroPaciente(navController: NavController) {
    var nome by remember { mutableStateOf(TextFieldValue("")) }
    var apelido by remember { mutableStateOf(TextFieldValue("")) }
    var idade by remember { mutableStateOf(TextFieldValue("")) }
    var regiao by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var senha by remember { mutableStateOf(TextFieldValue("")) }
    var senhaVisivel by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val database = FirebaseDatabase.getInstance()
    val databaseRef = database.getReference("pacientes")

    fun isValidEmail(email: String): Boolean {
        val emailPattern = android.util.Patterns.EMAIL_ADDRESS
        return emailPattern.matcher(email).matches()
    }

    fun savePatientData() {
        if (!isValidEmail(email.text)) {
            errorMessage = "Por favor, insira um email válido."
            return
        }

        if (senha.text.length < 6) {
            errorMessage = "A senha deve ter pelo menos 6 caracteres."
            return
        }

        val patientId = databaseRef.push().key ?: return
        val patient = mapOf(
            "nome" to nome.text,
            "apelido" to apelido.text,
            "idade" to idade.text,
            "regiao" to regiao.text,
            "email" to email.text,
            "senha" to senha.text // NÃO recomendável salvar senha como texto puro
        )
        databaseRef.child(patientId).setValue(patient)
            .addOnSuccessListener {
                Log.d("Firebase", "Dados salvos com sucesso!")
                navController.navigate("login")
            }
            .addOnFailureListener { exception ->
                errorMessage = "Erro ao salvar dados: ${exception.message}"
                Log.e("Firebase", errorMessage)
            }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFEC)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .border(2.dp, Color(0xFFB8D8B7), RoundedCornerShape(16.dp))
                .padding(16.dp)
                .background(Color(0xFFFFFFFF)),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registro de Paciente",
                color = Color(0xFF3D7B31),
                fontSize = 24.sp
            )

            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome", color = Color(0xFF3D7B31)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = apelido,
                onValueChange = { apelido = it },
                label = { Text("Apelido", color = Color(0xFF3D7B31)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = idade,
                onValueChange = { idade = it },
                label = { Text("Idade", color = Color(0xFF3D7B31)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = regiao,
                onValueChange = { regiao = it },
                label = { Text("Região", color = Color(0xFF3D7B31)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", color = Color(0xFF3D7B31)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = senha,
                onValueChange = { senha = it },
                label = { Text("Senha", color = Color(0xFF3D7B31)) },
                visualTransformation = if (senhaVisivel) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    Icon(
                        imageVector = if (senhaVisivel) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (senhaVisivel) "Ocultar senha" else "Exibir senha",
                        modifier = Modifier.clickable { senhaVisivel = !senhaVisivel },
                        tint = Color(0xFF3D7B31)
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }

            Button(
                onClick = {
                    savePatientData()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9EBD8E),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Registrar", fontSize = 16.sp)
            }
        }
    }
}
