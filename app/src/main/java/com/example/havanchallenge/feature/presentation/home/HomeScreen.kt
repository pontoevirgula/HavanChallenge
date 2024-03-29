package com.example.havanchallenge.feature.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.havanchallenge.feature.presentation.favorites.FavoriteViewModel

@Composable
fun HomeScreen(navHostController: NavHostController) {
    val favoriteViewModel = hiltViewModel<FavoriteViewModel>()
    val viewModel = hiltViewModel<ProductViewModel>()
    val productListState = viewModel.productListState.collectAsState().value


    if (productListState.products?.isEmpty() == true){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp, horizontal = 4.dp)
        ){
            items(productListState.products?.size!!){ index ->
                ProductItem(
                    productListState.products[index],
                    navHostController,
                    favoriteViewModel
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }


}