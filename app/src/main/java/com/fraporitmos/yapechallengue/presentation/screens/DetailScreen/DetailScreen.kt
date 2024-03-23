package com.fraporitmos.yapechallengue.presentation.screens.DetailScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.fraporitmos.yapechallengue.presentation.theme.dimens
import com.fraporitmos.yapechallengue.core.entities.Recipe
import com.fraporitmos.yapechallengue.presentation.navigation.routes.DetailsRoutes
import com.fraporitmos.yapechallengue.presentation.screens.DetailScreen.viewModel.RecipeOfflineViewModel
import com.fraporitmos.yapechallengue.presentation.screens.MapScreen.MarkerInfo
import com.google.gson.Gson


@Composable
fun DetailScreen(
    currentRecipe: Recipe,
    navController: NavHostController,
    saveViewModel: RecipeOfflineViewModel = hiltViewModel()
) {
    val isSaveRecipe by saveViewModel.isSaved.observeAsState()
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
    ) {

        val painter = rememberImagePainter(currentRecipe.imageUrl)
        var sizeImage by remember { mutableStateOf(IntSize.Zero) }
        val localContext = LocalContext.current
        val gradient = Brush.verticalGradient(
            colors = listOf(Color.Transparent, Color(0xFF250030)),
            startY = sizeImage.height.toFloat() / 3,
            endY = sizeImage.height.toFloat() / 1.1f
        )

        LaunchedEffect(navController) {
            saveViewModel.getOfflineRecipe()
        }

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {

            val (containerInfo) = createRefs()

            Box {
                Image(
                    painter = painter,
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                    modifier = Modifier
                        .height(MaterialTheme.dimens.heightImageDetail)
                        .fillMaxWidth()
                        .onGloballyPositioned {
                            sizeImage = it.size
                        })
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(gradient)
                )
                Box(
                    modifier = Modifier.padding(
                        top = MaterialTheme.dimens.medium3, start = 16.dp,
                    ),

                    ) {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                navController.popBackStack()
                            }
                            .width(MaterialTheme.dimens.iconSize)
                            .height(MaterialTheme.dimens.iconSize)
                            .background(
                                Color.White,
                                shape = RoundedCornerShape(50.dp)
                            )
                            .padding(8.dp),
                        tint = MaterialTheme.colorScheme.primary,
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                    )
                }
            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .constrainAs(
                    containerInfo
                ) {
                    bottom.linkTo(parent.bottom)
                }) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = currentRecipe.name,
                        color = Color.White,
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(end = 8.dp)
                    )

                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Icon(
                            modifier = Modifier
                                .clickable {
                                    saveViewModel.saveOfflineRecipe(currentRecipe)
                                    Toast
                                        .makeText(
                                            localContext,
                                            "Receta guardada",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                                .width(MaterialTheme.dimens.iconSize)
                                .height(MaterialTheme.dimens.iconSize)
                                .background(
                                    MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(50.dp)
                                )
                                .padding(8.dp),
                            tint = Color.White,
                            imageVector = Icons.Default.Save,
                            contentDescription = null,

                            )
                        Box(modifier = Modifier.padding(start = 8.dp)) {
                            Icon(
                                modifier = Modifier
                                    .clickable {
                                        val markerInfo = MarkerInfo(
                                            title = currentRecipe.name,
                                            description = currentRecipe.description,
                                            latitude = currentRecipe.latitude.toDouble(),
                                            longitude = currentRecipe.longitude.toDouble(),
                                            origin = currentRecipe.origin
                                        )
                                        val markerInfoJson = Gson().toJson(markerInfo)
                                        navController.navigate(DetailsRoutes.MapScreen.route + "/${markerInfoJson}")
                                    }
                                    .width(MaterialTheme.dimens.iconSize)
                                    .height(MaterialTheme.dimens.iconSize)
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        shape = RoundedCornerShape(50.dp)
                                    )
                                    .padding(8.dp),
                                tint = Color.White,
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = currentRecipe.description,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontWeight = FontWeight.Normal,
                    maxLines = 3,
                    color = Color.White,
                    lineHeight = MaterialTheme.dimens.lineHeight,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(color = Color(0xFFACACAC))
                )
            }

        }

        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight()
        ) {
            item {
                Text(
                    text = "Ingredientes",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = currentRecipe.ingredients.joinToString(", "),
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    lineHeight = MaterialTheme.dimens.lineHeight,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(color = Color(0xFFACACAC))
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Receta",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,

                    )
                Text(
                    text = currentRecipe.preparation,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    lineHeight = MaterialTheme.dimens.lineHeight,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(color = Color(0xFFACACAC))
                )
            }
        }
    }
}