package com.example.havanchallenge.feature.presentation.home

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.havanchallenge.feature.domain.model.Product
import com.example.havanchallenge.feature.util.Screen
import com.google.gson.Gson

@Composable
fun ProductItem(
    product: Product,
    navHostController: NavHostController
){
    val defaultColor = MaterialTheme.colorScheme.secondaryContainer
    val dominantColor by remember {
        mutableStateOf(defaultColor)
    }

   Column(
       modifier = Modifier
           .wrapContentHeight()
           .width(200.dp)
           .padding(8.dp)
           .clip(RoundedCornerShape(28.dp))
           .background(
               Brush.verticalGradient(
                   colors = listOf(
                       MaterialTheme.colorScheme.secondaryContainer,
                       dominantColor
                   )
               )
           )
           .clickable {
               val json = Uri.encode(Gson().toJson(product))
               navHostController.navigate(Screen.Details.rout + "/$json")
           }
   ){
       Spacer(modifier = Modifier.height(6.dp))
       Text(
           modifier = Modifier.padding(start = 16.dp, end = 8.dp),
           text = product.brand,
           color = Color.White,
           fontSize = 15.sp,
           maxLines = 1
       )

       Spacer(modifier = Modifier.height(6.dp))
       Text(
           modifier = Modifier.padding(start = 16.dp, end = 8.dp),
           text = product.description,
           color = Color.White,
           fontSize = 15.sp,
           maxLines = 1
       )

       Spacer(modifier = Modifier.height(6.dp))
       Text(
           modifier = Modifier.padding(start = 16.dp, end = 8.dp),
           text = product.price,
           color = Color.White,
           fontSize = 15.sp,
           maxLines = 1
       )
   }
}