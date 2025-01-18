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
    val background = Color(0xFFFFFEC)
    val pastelGreen = Color(0xFFE6F7E5)
    val lightGreen = Color(0xFFB8D8B7)
    val darkGreen = Color(0xFF3D7B31)
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

    var selectedTab by remember { mutableStateOf(0) } // Indica qual aba está selecionada

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("PsyConnect", color = AppColors.white) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppColors.darkGreen
                )
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                containerColor = AppColors.darkGreen,
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
                            selectedIconColor = AppColors.white,
                            unselectedIconColor = AppColors.white.copy(alpha = 0.7f),
                            indicatorColor = AppColors.lightGreen
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
            // Exibindo diretamente o conteúdo da aba selecionada
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
            .background(AppColors.pastelGreen),
        contentAlignment = Alignment.Center
    ) {
        Text("Tela de Consultas", color = AppColors.darkGreen, style = TextStyle(fontSize = MaterialTheme.typography.titleLarge.fontSize))
    }
}

@Composable
fun TelaInicio() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.lightGreen),
        contentAlignment = Alignment.Center
    ) {
        Text("Tela Inicial", color = AppColors.darkGreen, style = TextStyle(fontSize = MaterialTheme.typography.titleLarge.fontSize))
    }
}

@Composable
fun TelaPerfil() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.pastelGreen),
        contentAlignment = Alignment.Center
    ) {
        Text("Tela de Perfil", color = AppColors.darkGreen, style = TextStyle(fontSize = MaterialTheme.typography.titleLarge.fontSize))
    }
}

@Preview
@Composable
fun PreviewTelaInicial() {
    TelaInicial(userEmail = "user@domain.com", userName = "John Doe")
}
