package com.fraporitmos.yapechallengue.presentation.screens.HomeScreen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cast
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.fraporitmos.yapechallengue.presentation.theme.dimens
import com.fraporitmos.yapechallengue.R
import com.fraporitmos.yapechallengue.Utils
import com.fraporitmos.yapechallengue.presentation.main.viewModel.RecipeViewModel
import com.fraporitmos.yapechallengue.presentation.navigation.routes.BottombarRoutes
import com.fraporitmos.yapechallengue.presentation.screens.HomeScreen.composables.RecipeCard
import com.fraporitmos.yapechallengue.presentation.screens.HomeScreen.viewModel.SearchViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    recipesViewModel: RecipeViewModel,
    navController: NavHostController,
    searchViewModel: SearchViewModel  = hiltViewModel()

) {
    val recipeList by recipesViewModel.recipesList.observeAsState()
    val searchList by searchViewModel.searchList.observeAsState()

    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

        AnimatedVisibility( Utils.isConectionInternet()) {
            Scaffold {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top= 16.dp, start = 8.dp, end = 8.dp),
                    query = query,
                    onQueryChange = { currentSearch ->
                        query = currentSearch
                        searchViewModel.getRecipes(query)
                    },
                    onSearch = { active = true },
                    active = active,
                    onActiveChange = { active = it },
                    placeholder = {
                        Text(text = "Busca tu receta", fontSize = MaterialTheme.typography.labelMedium.fontSize)
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                    }) {

                    if (query.isNotEmpty()) {
                        searchList?.let {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.padding(top = 20.dp, bottom = 140.dp)
                            ) {
                                itemsIndexed(searchList!!) { _, recipe ->
                                    RecipeCard(
                                        recipe = recipe,
                                        navController
                                    )
                                }
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier.padding(top = 120.dp, bottom = 120.dp)
                ) {
                    recipeList?.let {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            itemsIndexed(recipeList!!) { _, recipe ->
                                RecipeCard(
                                    recipe = recipe,
                                    navController
                                )
                            }
                        }
                    }
                }
            }
        }
        AnimatedVisibility(! Utils.isConectionInternet()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {

                Image(
                    contentDescription = "Coil Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                        .height(MaterialTheme.dimens.imageCardHeight)
                        .width(MaterialTheme.dimens.imageCardWidth),
                    painter = rememberImagePainter( R.drawable.offline)
                )
                Text(
                    text = "Sin conexión a internet",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(16.dp)
                )
                Button(
                    modifier = Modifier.padding(start = 8.dp),
                    onClick = {
                        navController.navigate(BottombarRoutes.SaveScreen.route)

                    }) {
                    Text(text = stringResource(R.string.button_nav_save),
                        modifier = Modifier.padding(start = 4.dp),
                        fontSize = MaterialTheme.typography.titleSmall.fontSize
                    )
                }
            }
        }
}