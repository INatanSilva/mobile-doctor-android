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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import com.example.mobile_teste.ui.theme.AppColors

@OptIn(ExperimentalMaterial3Api::class)
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

    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()

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

        // Cadastrar no Firebase Authentication
        auth.createUserWithEmailAndPassword(email.text, senha.text)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid ?: return@addOnCompleteListener

                    // Criar um mapa com os dados do paciente
                    val patient = hashMapOf(
                        "nome" to nome.text,
                        "apelido" to apelido.text,
                        "idade" to idade.text,
                        "regiao" to regiao.text,
                        "email" to email.text
                    )

                    // Salvar os dados no Firestore na coleção "pacientes"
                    firestore.collection("pacientes").document(userId)
                        .set(patient)
                        .addOnSuccessListener {
                            Log.d("Firebase", "Dados salvos com sucesso!")
                            navController.navigate("login") // Redirecionar para a tela de login
                        }
                        .addOnFailureListener { exception ->
                            errorMessage = "Erro ao salvar dados: ${exception.message}"
                            Log.e("Firebase", errorMessage)
                        }
                } else {
                    errorMessage = "Erro ao cadastrar no Firebase: ${task.exception?.message}"
                    Log.e("Firebase", errorMessage)
                }
            }
    }

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
                text = "Registro de Paciente",
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
                    Icon(
                        imageVector = if (senhaVisivel) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (senhaVisivel) "Ocultar senha" else "Exibir senha",
                        modifier = Modifier.clickable { senhaVisivel = !senhaVisivel },
                        tint = AppColors.darkBackground // Ícone preto
                    )
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

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red, // Mensagem de erro em vermelho
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