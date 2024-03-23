package com.fraporitmos.yapechallengue.presentation.screens.MapScreen.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.fraporitmos.yapechallengue.core.entities.Recipe
import com.fraporitmos.yapechallengue.presentation.screens.MapScreen.MarkerInfo

@Composable
fun InfoWindow(
    recipe:MarkerInfo
){
    Column(
        modifier = Modifier
            .background(Color(0xFFFFFFFF))
            .width(240.dp)
            .padding(8.dp)
    ){
        Text(
            text = "${recipe.title} - ${recipe.origin}",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            color = Color(0xFF742284),
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = recipe.description,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 2,
            color = Color(0xFF431518),
            lineHeight = 15.sp,
            overflow = TextOverflow.Ellipsis,
        )
    }
}