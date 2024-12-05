package com.example.mobile_teste.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TelaInicial(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),  // Adiciona um pequeno padding
        contentAlignment = Alignment.Center // Centraliza o conteúdo
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,  // Centraliza o texto
            verticalArrangement = Arrangement.Center
        ) {
            // Texto de boas-vindas
            Text(
                text = "Seja bem-vindo(a)!",
                fontSize = 24.sp,  // Tamanho da fonte
                fontWeight = FontWeight.Bold,  // Negrito
                color = Color(0xFF3D7B31)  // Cor personalizada (verde escuro)
            )
        }
    }
}
