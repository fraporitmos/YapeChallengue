package com.fraporitmos.yapechallengue.presentation.screens.HomeScreen.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fraporitmos.yapechallengue.core.entities.Recipe
import com.fraporitmos.yapechallengue.domain.remote.SearchRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getRecipes: SearchRecipe
) : ViewModel() {

    var searchList = MutableLiveData<List<Recipe>>()

    fun getRecipes(query:String) {
        viewModelScope.launch {
            try {
                searchList.postValue(getRecipes.invoke(query))
            } catch (e: HttpException) {
                searchList.postValue(emptyList())
            } catch (e: Exception) {
                searchList.postValue(emptyList())
            }
        }
    }
}