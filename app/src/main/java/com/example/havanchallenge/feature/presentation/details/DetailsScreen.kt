package com.example.havanchallenge.feature.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.havanchallenge.feature.domain.model.Product

@Composable
fun DetailsScreen(product: Product?){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        product?.let {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = product.brand,
                fontSize = 19.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = product.description,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = product.price,
                fontSize = 19.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = product.category,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = product.productType,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

    }
}