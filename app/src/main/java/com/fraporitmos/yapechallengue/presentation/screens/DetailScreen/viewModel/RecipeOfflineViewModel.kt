package com.fraporitmos.yapechallengue.presentation.screens.DetailScreen.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fraporitmos.yapechallengue.core.entities.Recipe
import com.fraporitmos.yapechallengue.domain.persist.GetOfflineRecipes
import com.fraporitmos.yapechallengue.domain.persist.SaveOfflineRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class RecipeOfflineViewModel @Inject constructor(
    private val saveOfflineRecipe: SaveOfflineRecipe,
    private val getOfflineRecipe: GetOfflineRecipes
) : ViewModel() {

    var isSaved = MutableLiveData<Boolean>()
    var offlineRecipes = MutableLiveData<List<Recipe>>()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun saveOfflineRecipe(recipe: Recipe) {
        coroutineScope.launch {
            try {
                isSaved.postValue(true)
                saveOfflineRecipe.invoke(recipe)
            } catch (e: HttpException) {
                Log.d("SaveViewModelsss", e.toString())
                isSaved.postValue(false)
            } catch (e: Exception) {
                Log.d("SaveViewModelsss", e.toString())
                isSaved.postValue(false)
            }
        }
    }

    fun getOfflineRecipe() {
        coroutineScope.launch {
            try {
                offlineRecipes.postValue(getOfflineRecipe.invoke())
                Log.d(
                    "SaveViewModelsss",
                    "getOfflineRecipe: ${getOfflineRecipe.invoke()[0].description}"
                )
            } catch (e: HttpException) {
                Log.d("SaveViewModelsss", e.toString())
                offlineRecipes.postValue(emptyList())
            } catch (e: Exception) {
                Log.d("SaveViewModelsss", e.toString())
                offlineRecipes.postValue(emptyList())
            }
        }
    }
}