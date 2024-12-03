package com.example.mobile_teste

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.example.mobile_teste.ui.theme.MobiletesteTheme

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

    // Lista de especialidades na área de psicologia
    val especialidades = listOf(
        "Psicologia Clínica",
        "Psicologia Escolar",
        "Psicologia Organizacional",
        "Psicologia do Trabalho",
        "Psicologia Forense"
    )

    var expanded by remember { mutableStateOf(false) }

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
                // Nome
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

                // Apelido
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

                // Idade
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
                                text = { Text(text = especialidadeItem) }  // Corrigido para passar o texto corretamente
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Botão de registro
                Button(
                    onClick = {
                        // Lógica de registro do doutor
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor  = Color(0xFF9EBD8E))  // Cor do botão verde suave
                ) {
                    Text("Registrar Doutor", color = Color.White)
                }
            }
        }
    }
}
