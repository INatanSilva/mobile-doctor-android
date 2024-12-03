package com.example.mobile_teste

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.border
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable

class TelaEscolhaPacienteOuDoutor : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TelaEscolhaPacienteOuDoutorScreen()
        }
    }
}

@Composable
fun TelaEscolhaPacienteOuDoutorScreen() {
    // Cores conforme a paleta que você usou na tela de login
    val fundoTela = Color(0xFFFFFEC) // #FFFFEC
    val corBotao = Color(0xFF9EBD8E) // #9EBD8E
    val corTexto = Color(0xFF3D7B31) // #3D7B31
    val corBorda = Color( 0xFF8B9F8E) // #B8D8B7 (Verde mais escura para a borda)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fundoTela),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Botão de Paciente
            Button(
                onClick = { /* Ação para Paciente */ },
                colors = ButtonDefaults.buttonColors(containerColor = corBotao),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .height(48.dp)
                    .border(2.dp, corBorda, RoundedCornerShape(24.dp)), // Borda verde mais suave
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = "Paciente",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }

            // Botão de Doutor
            Button(
                onClick = { /* Ação para Doutor */ },
                colors = ButtonDefaults.buttonColors(containerColor = corBotao),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .border(2.dp, corBorda, RoundedCornerShape(24.dp)), // Borda verde mais suave
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = "Doutor",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }
    }
}
