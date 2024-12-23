import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// Definição das cores da paleta
object AppColors {
    val background = Color(0xFFFFFEC)
    val pastelGreen = Color(0xFFE6F7E5)
    val lightGreen = Color(0xFFB8D8B7)
    val darkGreen = Color(0xFF3D7B31)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaInicial(navController: NavController) {
    var isDrawerOpen by remember { mutableStateOf(false) }

    val drawerWidth by animateDpAsState(
        targetValue = if (isDrawerOpen) 250.dp else 0.dp,
        animationSpec = spring(stiffness = Spring.StiffnessLow)
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Conteúdo Principal
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { isDrawerOpen = !isDrawerOpen }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = AppColors.darkGreen
                            )
                        }
                    },
                    title = { Text("PsyConnect", color = AppColors.darkGreen) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = AppColors.pastelGreen
                    )
                )
            },
            content = { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(AppColors.background)
                        .padding(paddingValues)
                ) {
                    Text(
                        text = "Bem-vindo ao PsyConnect!",
                        color = AppColors.darkGreen,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        )

        // Drawer Overlay (escurece o fundo quando o Drawer está aberto)
        if (isDrawerOpen) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable { isDrawerOpen = false }
            )
        }

        // Drawer
        if (isDrawerOpen) { // O conteúdo do Drawer só será exibido se estiver aberto
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(drawerWidth)
                    .background(AppColors.lightGreen)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally // Centraliza horizontalmente
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Perfil do Usuário",
                        modifier = Modifier.size(80.dp),
                        tint = AppColors.darkGreen
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Nome do Usuário",
                        color = AppColors.darkGreen,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "email@exemplo.com",
                        color = AppColors.darkGreen,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
