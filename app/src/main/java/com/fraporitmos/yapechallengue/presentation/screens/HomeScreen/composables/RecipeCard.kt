package com.fraporitmos.yapechallengue.presentation.screens.HomeScreen.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.fraporitmos.yapechallengue.presentation.theme.dimens
import com.fraporitmos.yapechallengue.core.entities.Recipe
import com.fraporitmos.yapechallengue.presentation.main.viewModel.RecipeViewModel
import com.fraporitmos.yapechallengue.presentation.navigation.routes.BaseRoutes
import com.google.gson.Gson

@Composable
fun RecipeCard(
    recipe: Recipe, navController: NavController,
    recipesViewModel: RecipeViewModel = hiltViewModel(),

    ) {
    Card(
        shape = RoundedCornerShape(MaterialTheme.dimens.small3),
        elevation = CardDefaults.cardElevation(
            defaultElevation = MaterialTheme.dimens.small1,
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                val recipeJson = Gson().toJson(recipe)
                navController.navigate("${BaseRoutes.DETAIL}?recipe=$recipeJson")

            }
    ) {
        ConstraintLayout(
            modifier = Modifier
        ) {
            val (image, container) = createRefs()
            Image(
                contentDescription = "Coil Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .height(MaterialTheme.dimens.imageCardHeight)
                    .width(MaterialTheme.dimens.imageCardWidth),
                painter = rememberImagePainter(data = recipe.imageUrl),
            )
            Column(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
                    .fillMaxWidth()
                    .padding(MaterialTheme.dimens.paddingCard)
                    .background(color = Color(0xFFFFFFFF))
                    .constrainAs(container) {
                        top.linkTo(image.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp, start = 8.dp)
                        .background(
                            color = Color(0xFFF7C42B),
                            shape = RoundedCornerShape(10.dp)
                        )

                ) {
                    Text(
                        text = recipe.region,
                        fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        color = Color(0xFF431518),
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(
                            start = 12.dp,
                            end = 12.dp,
                            top = 2.dp,
                            bottom = 2.dp
                        )
                    )
                }

                Text(
                    text = recipe.name,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    color = Color(0xFF000000),
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = recipe.description,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2,
                    color = Color(0xFF68676F),
                    lineHeight = MaterialTheme.dimens.lineHeight,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 4.dp, bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        tint = Color(0xFFB29E46),
                        contentDescription = "Location"
                    )
                    Text(
                        text = recipe.origin,
                        fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        color = Color(0xFFB29E46),
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}