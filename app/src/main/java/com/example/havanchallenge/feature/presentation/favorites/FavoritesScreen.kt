package com.example.havanchallenge.feature.presentation.favorites

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.havanchallenge.feature.presentation.home.ProductItem


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoritesScreen(navHostController: NavHostController) {

    val viewModel = hiltViewModel<FavoriteViewModel>()
    val favoriteListState = viewModel.favoriteListState.collectAsState().value
    //val scope = rememberCoroutineScope()

    if (favoriteListState.favorites?.isEmpty() == true) {
        val snackBarHostState = remember {
            SnackbarHostState()
        }
        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = snackBarHostState
                ) {
                    LaunchedEffect(key1 = "") {
                        snackBarHostState.showSnackbar(
                            message = "Adicione algum produto como favorito",
                            duration = SnackbarDuration.Indefinite
                        )
                    }
                }
            }
        ) {
            Text(text = "Sem favoritos")
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
        ){
            items(favoriteListState.favorites?.size!!){ index ->
                ProductItem(
                    favoriteListState.favorites[index],
                    navHostController,
                    viewModel
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}