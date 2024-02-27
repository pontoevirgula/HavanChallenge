package com.example.havanchallenge.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.havanchallenge.core.ui.theme.HavanChallengeTheme
import com.example.havanchallenge.core.util.ViewUtil
import com.example.havanchallenge.core.util.ViewUtil.A_TO_Z
import com.example.havanchallenge.core.util.ViewUtil.HIGH_PRICE
import com.example.havanchallenge.core.util.ViewUtil.LOW_PRICE
import com.example.havanchallenge.core.util.ViewUtil.Z_TO_A
import com.example.havanchallenge.feature.domain.model.Product
import com.example.havanchallenge.feature.domain.model.ProductType
import com.example.havanchallenge.feature.presentation.details.DetailsScreen
import com.example.havanchallenge.feature.presentation.favorites.FavoritesScreen
import com.example.havanchallenge.feature.presentation.home.HomeScreen
import com.example.havanchallenge.feature.util.Screen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HavanChallengeTheme {
                SetBarColor(color = MaterialTheme.colorScheme.inverseOnSurface)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.rout
                    ) {
                        composable(Screen.Home.rout) {
                            CallHome(navController)
                        }

                        composable(
                            route = Screen.Details.rout + "/{product}",
                            arguments = listOf(
                                navArgument("product") {
                                    type = ProductType()
                                }
                            )
                        ) {
                            val product = it.arguments?.getParcelable<Product>("product")
                            DetailsScreen(product)
                        }

                        composable(route = Screen.Favorites.rout) {
                            FavoritesScreen(navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CallHome(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            MainAppBar(
                modifier = Modifier.fillMaxWidth(),
                navHostController
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(Screen.Favorites.rout)
                },
            ) {
                Icon(Icons.Filled.Favorite, "Floating action button.")
            }
        }

    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            HomeScreen(navHostController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(modifier: Modifier = Modifier, navHostController: NavHostController) {
    var menuExpanded by remember {
        mutableStateOf(false)
    }
    TopAppBar(
        title = {
            Text(
                text = "LISTA DE PRODUTOS",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        },
        modifier = modifier.shadow(2.dp),
        colors = topAppBarColors(
            MaterialTheme.colorScheme.inverseOnSurface
        ),
        actions = {
            IconButton(onClick = { menuExpanded = !menuExpanded }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "More",
                )
            }
            DropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = { menuExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text(text = "Menor Preço") },
                    onClick = {
                        ViewUtil.oderTypeSelected = LOW_PRICE
                        navHostController.navigate(Screen.Home.rout)
                    }
                )
                Divider()

                DropdownMenuItem(
                    text = { Text(text = "Maior Preço") },
                    onClick = {
                        ViewUtil.oderTypeSelected = HIGH_PRICE
                        navHostController.navigate(Screen.Home.rout)
                    }
                )
                Divider()

                DropdownMenuItem(
                    text = { Text(text = "A-Z") },
                    onClick = {
                        ViewUtil.oderTypeSelected = A_TO_Z
                        navHostController.navigate(Screen.Home.rout)
                    }
                )
                Divider()

                DropdownMenuItem(
                    text = { Text(text = "Z-A") },
                    onClick = {
                        ViewUtil.oderTypeSelected = Z_TO_A
                        navHostController.navigate(Screen.Home.rout)
                    }
                )
            }
        }
    )
}

@Composable
private fun SetBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(key1 = color) {
        systemUiController.setSystemBarsColor(color)
    }
}


