import android.util.Log
import android.widget.Toast
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
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(navController: NavController, onLogin: (String, String) -> Unit) {
    val auth = FirebaseAuth.getInstance() // Inicializa o FirebaseAuth

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFDEC)), // Fundo claro
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .border(2.dp, Color(0xFFB8D8B7), RoundedCornerShape(16.dp))
                    .padding(24.dp)
            ) {
                Text(
                    text = "Bem-vindo(a)",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3D7B31) // Verde escuro
                )
                Spacer(modifier = Modifier.height(16.dp))
                LoginForm(auth, navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(auth: FirebaseAuth, navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Campo de E-mail
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF3D7B31),
                unfocusedBorderColor = Color(0xFF9EBD8E),
                focusedLabelColor = Color(0xFF3D7B31),
                unfocusedLabelColor = Color(0xFF9EBD8E)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Campo de Senha
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF3D7B31),
                unfocusedBorderColor = Color(0xFF9EBD8E),
                focusedLabelColor = Color(0xFF3D7B31),
                unfocusedLabelColor = Color(0xFF9EBD8E)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Botão de Login
        Button(
            onClick = {
                loginUser(email, password, auth) { success, message ->
                    if (success) {
                        Toast.makeText(
                            navController.context,
                            "Login realizado com sucesso!",
                            Toast.LENGTH_SHORT
                        ).show()

                        // Navegar para a Tela Inicial após login
                        navController.navigate("telaInicial") {
                            popUpTo("login") { inclusive = true }
                        }
                    } else {
                        errorMessage = message
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3D7B31)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(48.dp),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(text = "Entrar", color = Color.White)
        }

        // Mensagem de erro
        errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Links
        TextButton(onClick = { navController.navigate("telaEscolhaPacienteOuDoutor") }) {
            Text(text = "Criar uma conta", color = Color(0xFF3D7B31))
        }
        TextButton(onClick = { /* Navegar para recuperar senha */ }) {
            Text(text = "Esqueci minha senha", color = Color(0xFF3D7B31))
        }
    }
}

fun loginUser(email: String, password: String, auth: FirebaseAuth, callback: (Boolean, String?) -> Unit) {
    if (email.isBlank() || password.isBlank()) {
        callback(false, "Por favor, preencha todos os campos.")
        return
    }

    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback(true, null)
            } else {
                callback(false, task.exception?.message ?: "Erro desconhecido")
            }
        }
}
