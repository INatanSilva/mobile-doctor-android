import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
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

object AppColors {
    val background = Color(0xFF000000)
    val primary = Color(0xFF1E1E1E)
    val accent = Color(0xFF2196F3)
    val white = Color(0xFFFFFFFF)
}

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
                title = { Text("PsyConnect", color = AppColors.white) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppColors.primary
                )
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                containerColor = AppColors.primary,
                contentColor = AppColors.white
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
                            selectedIconColor = AppColors.accent,
                            unselectedIconColor = AppColors.white.copy(alpha = 0.7f),
                            indicatorColor = AppColors.primary
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppColors.background)
                .padding(
                    start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                    top = paddingValues.calculateTopPadding(),
                    end = paddingValues.calculateEndPadding(LayoutDirection.Ltr),
                    bottom = paddingValues.calculateBottomPadding()
                )
        ) {
            when (selectedTab) {
                0 -> TelaConsultas()
                1 -> TelaInicio()
                2 -> TelaPerfil()
            }
        }
    }
}

@Composable
fun TelaConsultas() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.primary),
        contentAlignment = Alignment.Center
    ) {
        Text("Tela de Consultas", color = AppColors.accent, style = TextStyle(fontSize = MaterialTheme.typography.titleLarge.fontSize))
    }
}

@Composable
fun TelaInicio() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.primary),
        contentAlignment = Alignment.Center
    ) {
        Text("Tela Inicial", color = AppColors.accent, style = TextStyle(fontSize = MaterialTheme.typography.titleLarge.fontSize))
    }
}

@Composable
fun TelaPerfil() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.primary),
        contentAlignment = Alignment.Center
    ) {
        Text("Tela de Perfil", color = AppColors.accent, style = TextStyle(fontSize = MaterialTheme.typography.titleLarge.fontSize))
    }
}

@Preview
@Composable
fun PreviewTelaInicial() {
    TelaInicial(userEmail = "user@domain.com", userName = "John Doe")
}
