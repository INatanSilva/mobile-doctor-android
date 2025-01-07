import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

// Definição das cores da paleta
object AppColors {
    val background = Color(0xFFFFFEC)
    val pastelGreen = Color(0xFFE6F7E5)
    val lightGreen = Color(0xFFB8D8B7)
    val darkGreen = Color(0xFF3D7B31)
}

// ViewModel para gerenciar o estado do usuário
class UserViewModel : ViewModel() {
    var userName by mutableStateOf("Nome do Usuário")
        private set
    var userEmail by mutableStateOf("email@exemplo.com")
        private set

    fun setUser(name: String, email: String) {
        userName = name
        userEmail = email
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaInicial(
    navController: NavController,
    userViewModel: UserViewModel = viewModel(),
    userEmail: String,
    userName: String
) {
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
                                tint = AppColors.pastelGreen
                            )
                        }
                    },
                    title = { Text("PsyConnect", color = AppColors.pastelGreen) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = AppColors.darkGreen
                    )
                )
            },
            content = { paddingValues: PaddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(AppColors.background)
                        .padding(paddingValues)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    // LazyRow com os Cards
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(3) { index -> // Substitua 5 pelo número de itens que você deseja
                            CardItem(
                                imageUrl = "https://via.placeholder.com/150", // Substitua pela URL real da imagem
                                title = "Card $index",
                                navController = navController
                            )
                        }
                    }
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
                        text = userViewModel.userName, // Nome do usuário
                        color = AppColors.darkGreen,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = userViewModel.userEmail, // Email do usuário
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

@Composable
fun CardItem(imageUrl: String, title: String, navController: NavController) {


}
