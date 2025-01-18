import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

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
    navController: NavController,
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

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val rotaAtual = navBackStackEntry?.destination?.route

    val cards = List(3) { index -> "Card $index" }

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
                    .height(60.dp),
                containerColor = AppColors.darkGreen,
                contentColor = AppColors.white
            ) {
                navItems.forEachIndexed { index, item ->
                    val selecionado = rotaAtual == item.route

                    NavigationBarItem(
                        selected = selecionado,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
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
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(cards) { cardTitle ->
                    CardItem(
                        imageUrl = "https://via.placeholder.com/150",
                        title = cardTitle,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun CardItem(
    imageUrl: String,
    title: String,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .size(150.dp)
            .clickable {
                navController.navigate("detalhes/$title")
            },
        colors = CardDefaults.cardColors(
            containerColor = AppColors.pastelGreen
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.Gray)
            )

            Text(
                text = title,
                color = AppColors.darkGreen,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}