package com.example.mobile_teste

import LoginScreen
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.mobile_teste.ui.theme.MobiletesteTheme
import com.google.firebase.database.*

class MainActivity : ComponentActivity() {
    // Variáveis para o Firebase Database
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializando o Firebase Database
        database = FirebaseDatabase.getInstance()
        databaseRef = database.getReference("users") // Referência ao nó "users"

        // Exemplo: Salvando um dado inicial no banco
        saveInitialData()

        // Teste local: Ler os dados ao iniciar o aplicativo
        readDatabase()

        setContent {
            MobiletesteTheme {
                // Configurando o NavController
                val navController = rememberNavController()

                // Estado para controlar o carregamento
                var isLoading by remember { mutableStateOf(true) }

                // Simula um carregamento de 3 segundos
                LaunchedEffect(Unit) {
                    kotlinx.coroutines.delay(3000)  // Simula o delay de carregamento
                    isLoading = false
                }

                // Exibir ícone de carregamento ou conteúdo baseado no estado
                if (isLoading) {
                    // Tela de carregamento
                    LoadingScreen()
                } else {
                    // Tela de navegação normal após o carregamento
                    NavHost(
                        navController = navController,
                        startDestination = "telaEscolhaPacienteOuDoutor"
                    ) {
                        // Tela de escolha de Paciente ou Doutor
                        composable("telaEscolhaPacienteOuDoutor") {
                            TelaEscolhaPacienteOuDoutor(navController = navController)
                        }

                        // Tela de login
                        composable("login") {
                            LoginScreen(navController) // Corrigido o nome da tela LoginScreen
                        }

                        // Tela de registro de paciente
                        composable("registroPaciente") {
                            TelaRegistroPaciente(navController = navController)
                        }

                        // Tela de registro do doutor
                        composable("telaRegistroDoutor") {
                            TelaRegistroDoutor(navController = navController)
                        }
                    }
                }
            }
        }
    }

    // Função para salvar dados iniciais no banco
    private fun saveInitialData() {
        val userId = databaseRef.push().key ?: return // Gera um ID único para o usuário
        val user = mapOf("name" to "John Doe", "email" to "johndoe@example.com")
        databaseRef.child(userId).setValue(user)
            .addOnSuccessListener {
                Log.d("Firebase", "Dados salvos com sucesso!")
            }
            .addOnFailureListener { exception ->
                Log.e("Firebase", "Erro ao salvar dados: ${exception.message}")
            }
    }

    // Função para ler dados do banco
    private fun readDatabase() {
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Percorre todos os filhos do nó "users"
                for (userSnapshot in snapshot.children) {
                    val user = userSnapshot.value
                    Log.d("Firebase", "Usuário encontrado: $user")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Erro ao ler os dados: ${error.message}")
            }
        })
    }

    // Função para exibir a tela de carregamento
    @Composable
    fun LoadingScreen() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()  // Ícone de carregamento
        }
    }
}
