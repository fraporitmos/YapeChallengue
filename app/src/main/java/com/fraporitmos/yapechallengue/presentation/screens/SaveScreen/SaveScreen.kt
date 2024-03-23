package com.fraporitmos.yapechallengue.presentation.screens.SaveScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.fraporitmos.yapechallengue.presentation.theme.dimens
import com.fraporitmos.yapechallengue.R
import com.fraporitmos.yapechallengue.presentation.screens.DetailScreen.viewModel.RecipeOfflineViewModel
import com.fraporitmos.yapechallengue.presentation.screens.HomeScreen.composables.RecipeCard

@Composable
fun SaveScreen(
    navController: NavController,
    saveViewModel: RecipeOfflineViewModel = hiltViewModel()
) {
    val listRecipeOffline by saveViewModel.offlineRecipes.observeAsState()
    LaunchedEffect(navController) {
        saveViewModel.getOfflineRecipe()
    }
    Column(
        modifier = Modifier.padding(top = 40.dp, bottom = 120.dp)
    ) {
        listRecipeOffline?.let {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                itemsIndexed(listRecipeOffline!!) { _, recipe ->
                    RecipeCard(
                        recipe = recipe,
                        navController
                    )
                }
            }
        }
        AnimatedVisibility(visible = listRecipeOffline.isNullOrEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    contentDescription = "Coil Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                        .height(MaterialTheme.dimens.imageCardHeight)
                        .width(MaterialTheme.dimens.imageCardWidth),
                    painter = rememberImagePainter(R.drawable.empty)
                )
                Text(
                    text = "No tienes recetas guardadas.",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

    }

}