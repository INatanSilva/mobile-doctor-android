package com.example.mobile_teste

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun TelaRegistroDoutor(navController: NavController) {
    TelaRegistroDoutorScreen(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaRegistroDoutorScreen(navController: NavController) {
    // Definindo os estados para os campos de texto
    var nome by remember { mutableStateOf(TextFieldValue()) }
    var apelido by remember { mutableStateOf(TextFieldValue()) }
    var idade by remember { mutableStateOf(TextFieldValue()) }
    var regiao by remember { mutableStateOf(TextFieldValue()) }
    var especialidade by remember { mutableStateOf("") }
    var email by remember { mutableStateOf(TextFieldValue()) }
    var senha by remember { mutableStateOf(TextFieldValue()) }

    // Lista de especialidades na área de psicologia
    val especialidades = listOf(
        "Psicologia Clínica",
        "Psicologia Escolar",
        "Psicologia Organizacional",
        "Psicologia do Trabalho",
        "Psicologia Forense"
    )

    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current  // Acessando o contexto

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFFFFEC)),  // Cor de fundo da tela
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Caixa branca para os campos de entrada
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color(0xFFB8D8B7))  // Cor da borda verde suave
                .background(Color.White)
                .padding(16.dp)
        ) {
            Column {

                Text("Nome", color = Color(0xFF3D7B31))  // Cor do texto principal (verde escuro)
                BasicTextField(
                    value = nome,
                    onValueChange = { nome = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .border(1.dp, Color(0xFFB8D8B7))  // Cor da borda verde suave
                        .padding(8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Apelido", color = Color(0xFF3D7B31))  // Cor do texto principal (verde escuro)
                BasicTextField(
                    value = apelido,
                    onValueChange = { apelido = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .border(1.dp, Color(0xFFB8D8B7))  // Cor da borda verde suave
                        .padding(8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Idade", color = Color(0xFF3D7B31))  // Cor do texto principal (verde escuro)
                BasicTextField(
                    value = idade,
                    onValueChange = { idade = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .border(1.dp, Color(0xFFB8D8B7))  // Cor da borda verde suave
                        .padding(8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Região
                Text("Região", color = Color(0xFF3D7B31))  // Cor do texto principal (verde escuro)
                BasicTextField(
                    value = regiao,
                    onValueChange = { regiao = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .border(1.dp, Color(0xFFB8D8B7))  // Cor da borda verde suave
                        .padding(8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // E-mail
                Text("E-mail", color = Color(0xFF3D7B31))  // Cor do texto principal (verde escuro)
                BasicTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .border(1.dp, Color(0xFFB8D8B7))  // Cor da borda verde suave
                        .padding(8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Senha
                Text("Senha", color = Color(0xFF3D7B31))  // Cor do texto principal (verde escuro)
                BasicTextField(
                    value = senha,
                    onValueChange = { senha = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .border(1.dp, Color(0xFFB8D8B7))  // Cor da borda verde suave
                        .padding(8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Especialidade
                Text("Especialidade", color = Color(0xFF3D7B31))  // Cor do texto principal (verde escuro)

                Box(modifier = Modifier.fillMaxWidth()) {
                    TextField(
                        value = especialidade,
                        onValueChange = { especialidade = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .border(1.dp, Color(0xFFB8D8B7))  // Cor da borda verde suave
                            .padding(8.dp),
                        label = { Text("Escolha uma especialidade") },
                        trailingIcon = {
                            IconButton(onClick = { expanded = !expanded }) {
                                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                            }
                        }
                    )

                    // DropdownMenu
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        especialidades.forEach { especialidadeItem ->
                            DropdownMenuItem(
                                onClick = {
                                    especialidade = especialidadeItem
                                    expanded = false
                                },
                                text = { Text(text = especialidadeItem) }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Botão de registro
                Button(
                    onClick = {
                        // Verifica se todos os campos foram preenchidos
                        if (nome.text.isNotEmpty() && apelido.text.isNotEmpty() && idade.text.isNotEmpty() &&
                            regiao.text.isNotEmpty() && especialidade.isNotEmpty() && email.text.isNotEmpty() && senha.text.isNotEmpty()) {
                            registrarDoutor(
                                nome.text, apelido.text, idade.text, regiao.text, especialidade, email.text, senha.text, context, navController
                            )
                        } else {
                            Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9EBD8E))  // Cor do botão verde suave
                ) {
                    Text("Registrar", color = Color.White)
                }
            }
        }
    }
}

fun registrarDoutor(
    nome: String, apelido: String, idade: String, regiao: String,
    especialidade: String, email: String, senha: String,
    context: android.content.Context, navController: NavController
) {
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val userId = auth.currentUser?.uid
            val doutor = hashMapOf(
                "nome" to nome,
                "apelido" to apelido,
                "idade" to idade,
                "regiao" to regiao,
                "especialidade" to especialidade,
                "email" to email
            )

            if (userId != null) {
                db.collection("doutores").document(userId).set(doutor)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Doutor registrado com sucesso!", Toast.LENGTH_SHORT).show()
                        navController.navigate("login") // Redireciona para a tela de login
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Erro ao salvar dados: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        } else {
            Toast.makeText(context, "Erro ao registrar: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
        }
    }
}


data class Doutor(
    val nome: String,
    val apelido: String,
    val idade: String,
    val regiao: String,
    val especialidade: String,
    val email: String
)
