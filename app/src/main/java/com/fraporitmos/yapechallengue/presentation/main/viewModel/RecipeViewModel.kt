package com.fraporitmos.yapechallengue.presentation.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fraporitmos.yapechallengue.core.entities.Recipe
import com.fraporitmos.yapechallengue.domain.remote.GetRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val getRecipes: GetRecipes
) : ViewModel() {

    var recipesList = MutableLiveData<List<Recipe>>()

    fun getRecipes() {
        viewModelScope.launch {
            try {
                recipesList.postValue(getRecipes.invoke())
            } catch (e: HttpException) {
                recipesList.postValue(emptyList())
            } catch (e: Exception) {
                recipesList.postValue(emptyList())
            }
        }
    }
}