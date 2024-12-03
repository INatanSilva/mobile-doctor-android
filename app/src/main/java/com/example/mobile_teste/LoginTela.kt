import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class LoginTela : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
    }
}

@Composable
fun LoginScreen() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFEC)) // #ffffec (Fundo claro)
            ,
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(16.dp)) // Fundo branco da coluna
                    .border(2.dp, Color(0xFFB8D8B7), RoundedCornerShape(20.dp)) // Borda verde mais suave
                    .padding(24.dp)
            ) {
                Text(
                    text = "Bem-vindo(a)",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3D7B31) // #3d7b31 (Verde escuro)
                )
                Spacer(modifier = Modifier.height(16.dp))
                LoginForm()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) } // Controla a visibilidade da senha

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Campo de E-mail
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF3D7B31), // #3d7b31 (Verde escuro) para borda
                unfocusedBorderColor = Color(0xFF9EBD8E), // #9ebd8e (Verde suave) para borda
                focusedLabelColor = Color(0xFF3D7B31), // #3d7b31 (Verde escuro) para label
                unfocusedLabelColor = Color(0xFF9EBD8E) // #9ebd8e (Verde suave) para label
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Campo de Senha com Alternância de Visibilidade
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                val description = if (passwordVisible) "Ocultar senha" else "Mostrar senha"
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = icon, contentDescription = description)
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF3D7B31), // #3d7b31 (Verde escuro) para borda
                unfocusedBorderColor = Color(0xFF9EBD8E), // #9ebd8e (Verde suave) para borda
                focusedLabelColor = Color(0xFF3D7B31), // #3d7b31 (Verde escuro) para label
                unfocusedLabelColor = Color(0xFF9EBD8E) // #9ebd8e (Verde suave) para label
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Botão de Entrar
        Button(
            onClick = { /* Ação ao clicar no botão */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9EBD8E)), // #9ebd8e (Verde suave)
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(48.dp),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(text = "Entrar", color = Color.White)
        }

        // Links para outras ações
        TextButton(onClick = { /* Ação para criar conta */ }) {
            Text(
                text = "Criar uma conta",
                color = Color(0xFF3D7B31), // #3d7b31 (Verde escuro)
                fontWeight = FontWeight.Medium
            )
        }
        TextButton(onClick = { /* Ação para recuperar senha */ }) {
            Text(
                text = "Esqueci minha senha",
                color = Color(0xFF3D7B31), // #3d7b31 (Verde escuro)
                fontWeight = FontWeight.Medium
            )
        }
    }
}
