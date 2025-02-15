package com.example.mobile_teste

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.mobile_teste.ui.theme.AppColors

@Composable
fun TelaRegistroDoutor(navController: NavController) {
    TelaRegistroDoutorScreen(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaRegistroDoutorScreen(navController: NavController) {
    // Definindo os estados para os campos de texto
    var nome by remember { mutableStateOf(TextFieldValue("")) }
    var apelido by remember { mutableStateOf(TextFieldValue("")) }
    var idade by remember { mutableStateOf(TextFieldValue("")) }
    var regiao by remember { mutableStateOf(TextFieldValue("")) }
    var especialidade by remember { mutableStateOf("") }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var senha by remember { mutableStateOf(TextFieldValue("")) }
    var senhaVisivel by remember { mutableStateOf(false) } // Estado para mostrar/esconder senha
    var errorMessage by remember { mutableStateOf("") }

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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.lightBackground), // Fundo branco
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(16.dp)) // Sombra para profundidade
                .background(AppColors.darkWhite, shape = RoundedCornerShape(16.dp)) // Fundo branco mais forte
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registro de Doutor",
                color = AppColors.darkBackground, // Texto preto
                fontSize = 24.sp
            )

            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome", color = AppColors.darkBackground) }, // Texto preto
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = AppColors.darkAccent, // Borda azul quando focada
                    unfocusedBorderColor = AppColors.darkBackground, // Borda preta quando não focada
                    cursorColor = AppColors.darkAccent, // Cursor azul
                    focusedLabelColor = AppColors.darkAccent, // Label azul quando focada
                    unfocusedLabelColor = AppColors.darkBackground // Label preta quando não focada
                )
            )

            OutlinedTextField(
                value = apelido,
                onValueChange = { apelido = it },
                label = { Text("Apelido", color = AppColors.darkBackground) }, // Texto preto
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = AppColors.darkAccent,
                    unfocusedBorderColor = AppColors.darkBackground,
                    cursorColor = AppColors.darkAccent,
                    focusedLabelColor = AppColors.darkAccent,
                    unfocusedLabelColor = AppColors.darkBackground
                )
            )

            OutlinedTextField(
                value = idade,
                onValueChange = { idade = it },
                label = { Text("Idade", color = AppColors.darkBackground) }, // Texto preto
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = AppColors.darkAccent,
                    unfocusedBorderColor = AppColors.darkBackground,
                    cursorColor = AppColors.darkAccent,
                    focusedLabelColor = AppColors.darkAccent,
                    unfocusedLabelColor = AppColors.darkBackground
                )
            )

            OutlinedTextField(
                value = regiao,
                onValueChange = { regiao = it },
                label = { Text("Região", color = AppColors.darkBackground) }, // Texto preto
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = AppColors.darkAccent,
                    unfocusedBorderColor = AppColors.darkBackground,
                    cursorColor = AppColors.darkAccent,
                    focusedLabelColor = AppColors.darkAccent,
                    unfocusedLabelColor = AppColors.darkBackground
                )
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", color = AppColors.darkBackground) }, // Texto preto
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = AppColors.darkAccent,
                    unfocusedBorderColor = AppColors.darkBackground,
                    cursorColor = AppColors.darkAccent,
                    focusedLabelColor = AppColors.darkAccent,
                    unfocusedLabelColor = AppColors.darkBackground
                )
            )

            OutlinedTextField(
                value = senha,
                onValueChange = { senha = it },
                label = { Text("Senha", color = AppColors.darkBackground) }, // Texto preto
                visualTransformation = if (senhaVisivel) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { senhaVisivel = !senhaVisivel }) {
                        Icon(
                            imageVector = if (senhaVisivel) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = if (senhaVisivel) "Ocultar senha" else "Exibir senha",
                            tint = AppColors.darkBackground // Ícone preto
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = AppColors.darkAccent,
                    unfocusedBorderColor = AppColors.darkBackground,
                    cursorColor = AppColors.darkAccent,
                    focusedLabelColor = AppColors.darkAccent,
                    unfocusedLabelColor = AppColors.darkBackground
                )
            )

            // Dropdown para especialidade
            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = especialidade,
                    onValueChange = { especialidade = it },
                    label = { Text("Especialidade", color = AppColors.darkBackground) }, // Texto preto
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Dropdown",
                                tint = AppColors.darkBackground // Ícone preto
                            )
                        }
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = AppColors.darkAccent,
                        unfocusedBorderColor = AppColors.darkBackground,
                        cursorColor = AppColors.darkAccent,
                        focusedLabelColor = AppColors.darkAccent,
                        unfocusedLabelColor = AppColors.darkBackground
                    )
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
                            text = { Text(text = especialidadeItem, color = AppColors.darkBackground) } // Texto preto
                        )
                    }
                }
            }

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red, // Mensagem de erro em vermelho
                    fontSize = 14.sp
                )
            }

            Button(
                onClick = {
                    // Verifica se todos os campos foram preenchidos
                    if (nome.text.isNotEmpty() && apelido.text.isNotEmpty() && idade.text.isNotEmpty() &&
                        regiao.text.isNotEmpty() && especialidade.isNotEmpty() && email.text.isNotEmpty() && senha.text.isNotEmpty()) {
                        registrarDoutor(
                            nome.text, apelido.text, idade.text, regiao.text, especialidade, email.text, senha.text, context, navController
                        )
                    } else {
                        errorMessage = "Preencha todos os campos"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.darkBackground, // Botão preto
                    contentColor = AppColors.lightPrimary // Texto branco
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Registrar", fontSize = 16.sp)
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