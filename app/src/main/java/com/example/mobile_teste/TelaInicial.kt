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
                title = { Text("PsyConnect", color = textColor) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = primaryColor
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
                0 -> TelaConsultas(primaryColor, accentColor, textColor)
                1 -> TelaInicio(primaryColor, accentColor, textColor)
                2 -> TelaPerfil(primaryColor, accentColor, textColor)
            }
        }
    }
}

@Composable
fun TelaConsultas(primaryColor: Color, accentColor: Color, textColor: Color) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryColor),
        contentAlignment = Alignment.Center
    ) {
        Text("Tela de Consultas", color = accentColor, style = TextStyle(fontSize = MaterialTheme.typography.titleLarge.fontSize))
    }
}

@Composable
fun TelaInicio(primaryColor: Color, accentColor: Color, textColor: Color) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryColor)
            .padding(16.dp)
    ) {
        // Cabeçalho
        Text(
            "Olá, Marina",
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
            .width(200.dp),
        colors = CardDefaults.cardColors(containerColor = accentColor.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        color = accentColor.copy(alpha = 0.2f),
                        shape = MaterialTheme.shapes.medium
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(iniciais, color = textColor)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(nome, color = textColor, style = MaterialTheme.typography.titleMedium)
            Text(especialidade, color = textColor.copy(alpha = 0.7f))
            Row(modifier = Modifier.padding(vertical = 8.dp)) {
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
