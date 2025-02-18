import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import com.example.mobile_teste.ui.theme.AppColors
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.material.icons.filled.Star

data class BottomNavItem(
    val route: String,
    val icon: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaInicial(
    userEmail: String,
    userName: String
) {
    var isDarkMode by remember { mutableStateOf(true) }
    val backgroundColor = if (isDarkMode) AppColors.darkBackground else AppColors.lightBackground
    val primaryColor = if (isDarkMode) AppColors.darkPrimary else AppColors.lightPrimary
    val accentColor = if (isDarkMode) AppColors.darkAccent else AppColors.lightAccent
    val textColor = if (isDarkMode) AppColors.darkWhite else AppColors.lightBlack

    val navItems = listOf(
        BottomNavItem(
            route = "consultas",
            icon = Icons.Filled.DateRange
        ),
        BottomNavItem(
            route = "inicio",
            icon = Icons.Filled.Home
        ),
        BottomNavItem(
            route = "perfil",
            icon = Icons.Filled.AccountCircle
        )
    )

    var selectedTab by remember { mutableStateOf(1) } // Começa na tela de início

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Column {
                        Text(
                            "PsyConnect", 
                            color = textColor,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = if (isDarkMode) Color(0xFF2C2C2C) else Color.White.copy(alpha = 0.9f)
                ),
                actions = {
                    IconButton(onClick = { isDarkMode = !isDarkMode }) {
                        Icon(
                            imageVector = if (isDarkMode) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                            contentDescription = "Alternar Tema",
                            tint = textColor
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                containerColor = primaryColor,
                contentColor = textColor
            ) {
                navItems.forEachIndexed { index, item ->
                    val selecionado = selectedTab == index
                    NavigationBarItem(
                        selected = selecionado,
                        onClick = {
                            selectedTab = index // Mudança imediata de aba
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.route,
                                modifier = Modifier.size(26.dp)
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = accentColor,
                            unselectedIconColor = textColor.copy(alpha = 0.7f),
                            indicatorColor = primaryColor
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(
                    start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                    top = paddingValues.calculateTopPadding(),
                    end = paddingValues.calculateEndPadding(LayoutDirection.Ltr),
                    bottom = paddingValues.calculateBottomPadding()
                )
        ) {
            when (selectedTab) {
                0 -> TelaConsultas(primaryColor, accentColor, textColor, isDarkMode)
                1 -> TelaInicio(primaryColor, accentColor, textColor, userName)
                2 -> TelaPerfil(primaryColor, accentColor, textColor)
            }
        }
    }
}

@Composable
fun TelaConsultas(primaryColor: Color, accentColor: Color, textColor: Color, isDarkMode: Boolean) {
    val cardBackgroundColor = if (isDarkMode) Color(0xFF2C2C2C) else Color(0xFFF5F5F5)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryColor)
            .padding(16.dp)
    ) {
        Text(
            "Seus Canais",
            color = textColor,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Grid de canais
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Primeira linha com Suporte e Comunidade
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Suporte
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = cardBackgroundColor)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            "Suporte",
                            color = Color(0xFF2196F3),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            "5 mensagens novas",
                            color = textColor.copy(alpha = 0.7f),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        Button(
                            onClick = { },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                            modifier = Modifier.align(Alignment.Start)
                        ) {
                            Text("Acessar")
                        }
                    }
                }

                // Comunidade
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = cardBackgroundColor)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            "Comunidade",
                            color = Color(0xFF4CAF50),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            "2 mensagens novas",
                            color = textColor.copy(alpha = 0.7f),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        Button(
                            onClick = { },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                            modifier = Modifier.align(Alignment.Start)
                        ) {
                            Text("Acessar")
                        }
                    }
                }
            }

            // Segunda linha com Meu Psicólogo
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = cardBackgroundColor)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        "Meu Psicólogo",
                        color = Color(0xFFFFA000),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        "Próxima sessão: hoje",
                        color = textColor.copy(alpha = 0.7f),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA000)),
                        modifier = Modifier.align(Alignment.Start)
                    ) {
                        Text("Acessar")
                    }
                }
            }
        }

        // Botão Ver Todos os Canais
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                modifier = Modifier.width(200.dp) // Largura fixa de 200.dp
            ) {
                Text("Ver Todos os Canais")
            }
        }
    }
}

@Composable
fun TelaInicio(primaryColor: Color, accentColor: Color, textColor: Color, userName: String) {
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    var nomeUsuario by remember { mutableStateOf("") }
    val userId = auth.currentUser?.uid

    LaunchedEffect(userId) {
        userId?.let {
            val pacientesRef = db.collection("pacientes").document(it)
            val doutoresRef = db.collection("doutores").document(it)

            pacientesRef.get().addOnSuccessListener { document ->
                if (document.exists()) {
                    nomeUsuario = document.getString("nome") ?: "Visitante"
                } else {
                    doutoresRef.get().addOnSuccessListener { doc ->
                        if (doc.exists()) {
                            nomeUsuario = doc.getString("nome") ?: "Visitante"
                        }
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryColor)
            .padding(16.dp)
    ) {
        Text(
            "Olá, ${nomeUsuario.ifEmpty { "Visitante" }}",
            color = textColor,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            "Encontre seu terapeuta",
            color = textColor,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(top = 8.dp)
        )

        // Barra de pesquisa
        OutlinedTextField(
            value = "",
            onValueChange = { },
            placeholder = { Text("Buscar por especialidade, nome...", color = textColor.copy(alpha = 0.6f)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = textColor.copy(alpha = 0.3f),
                focusedBorderColor = accentColor
            )
        )

        // Tags de categorias
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(vertical = 8.dp)
        ) {
            listOf("Depressão", "Ansiedade", "Estresse", "Mais...").forEach { categoria ->
                Surface(
                    onClick = { },
                    color = accentColor.copy(alpha = 0.1f),
                    modifier = Modifier.padding(end = 8.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = categoria,
                        color = accentColor,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                }
            }
        }

        // Seção Recomendados
        Text(
            "Recomendados para você",
            color = textColor,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Cards dos terapeutas
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
        ) {
            TerapeutaCard(
                iniciais = "DR",
                nome = "Dra. Regina Silva",
                especialidade = "Psicóloga • TCC",
                avaliacao = 4.1f,
                accentColor = accentColor,
                textColor = textColor
            )
            TerapeutaCard(
                iniciais = "JL",
                nome = "Dr. João Lima",
                especialidade = "Psicoterapeuta • Gestalt",
                avaliacao = 4.9f,
                accentColor = accentColor,
                textColor = textColor
            )
        }

        // Próxima sessão
        Text(
            "Sua próxima sessão",
            color = textColor,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = primaryColor)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = accentColor.copy(alpha = 0.2f),
                                shape = MaterialTheme.shapes.medium
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("RS", color = textColor)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Dra. Regina Silva", color = textColor)
                        Text("Hoje • 16:30 - 17:30", color = textColor.copy(alpha = 0.7f))
                    }
                }
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) {
                    Text("Entrar")
                }
            }
        }
    }
}

@Composable
fun TerapeutaCard(
    iniciais: String,
    nome: String,
    especialidade: String,
    avaliacao: Float,
    accentColor: Color,
    textColor: Color
) {
    Card(
        modifier = Modifier
            .padding(end = 16.dp)
            .width(180.dp),
        colors = CardDefaults.cardColors(containerColor = accentColor.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .height(220.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(
                        color = accentColor.copy(alpha = 0.2f),
                        shape = MaterialTheme.shapes.medium
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    iniciais, 
                    color = textColor,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(
                    nome, 
                    color = textColor, 
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1
                )
                Text(
                    especialidade, 
                    color = textColor.copy(alpha = 0.7f),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1
                )
            }
            
            Row(
                modifier = Modifier.padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(5) { index ->
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = if (index < avaliacao) Color(0xFFFFC107) else textColor.copy(alpha = 0.3f),
                        modifier = Modifier.size(16.dp)
                    )
                }
                Text(
                    " $avaliacao",
                    color = textColor.copy(alpha = 0.7f),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agendar")
            }
        }
    }
}

@Composable
fun TelaPerfil(primaryColor: Color, accentColor: Color, textColor: Color) {
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    var userName by remember { mutableStateOf("Carregando...") }
    var userEmail by remember { mutableStateOf("Carregando...") }
    val userId = auth.currentUser?.uid

    LaunchedEffect(userId) {
        userId?.let {
            val pacientesRef = db.collection("pacientes").document(it)
            val doutoresRef = db.collection("doutores").document(it)

            pacientesRef.get().addOnSuccessListener { document ->
                if (document.exists()) {
                    userName = document.getString("nome") ?: "Nome não encontrado"
                    userEmail = document.getString("email") ?: "Email não encontrado"
                } else {
                    // Se não for paciente, tenta buscar como doutor
                    doutoresRef.get().addOnSuccessListener { doc ->
                        if (doc.exists()) {
                            userName = doc.getString("nome") ?: "Nome não encontrado"
                            userEmail = doc.getString("email") ?: "Email não encontrado"
                        } else {
                            userName = "Usuário não encontrado"
                            userEmail = "---"
                        }
                    }.addOnFailureListener {
                        userName = "Erro ao carregar"
                        userEmail = "Erro ao carregar"
                    }
                }
            }.addOnFailureListener {
                userName = "Erro ao carregar"
                userEmail = "Erro ao carregar"
            }
        } ?: run {
            userName = "Usuário não logado"
            userEmail = "---"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = "Perfil",
            modifier = Modifier.size(120.dp),
            tint = accentColor
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = userName, color = textColor, style = MaterialTheme.typography.headlineSmall)
        Text(text = userEmail, color = textColor.copy(alpha = 0.7f), style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { /* Ação de editar perfil */ }, colors = ButtonDefaults.buttonColors(containerColor = accentColor)) {
            Text("Editar Perfil", color = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { auth.signOut() }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
            Text("Sair", color = Color.White)
        }
    }
}
