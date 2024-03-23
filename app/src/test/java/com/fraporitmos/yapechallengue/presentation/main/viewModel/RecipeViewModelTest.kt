package com.fraporitmos.yapechallengue.presentation.main.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fraporitmos.yapechallengue.utils.MainCoroutineRule
import com.fraporitmos.yapechallengue.domain.remote.GetRecipes
import com.fraporitmos.yapechallengue.data.remote.repository.ApplicationRemoteRepository
import com.fraporitmos.yapechallengue.data.remote.services.RecipeApiService
import com.fraporitmos.yapechallengue.dataAccess.JSONFileLoader
import com.fraporitmos.yapechallengue.utils.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var mainViewModel: RecipeViewModel
    private lateinit var service: RecipeApiService
    private lateinit var getRecipes: GetRecipes

    companion object {
        private lateinit var retrofit: Retrofit

        @BeforeClass
        @JvmStatic
        fun setUpCommon() {
            retrofit = Retrofit.Builder()
                .baseUrl("http://demo1707878.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    @Before
    fun setUp() {
        service = retrofit.create(RecipeApiService::class.java)
        getRecipes = GetRecipes(ApplicationRemoteRepository(service))
        mainViewModel = RecipeViewModel(getRecipes)
    }

    @Test
    fun `Check recipes is NotNull`() {
        runBlocking {
            val result = service.getRecipes()
            assertThat(result, `is`(notNullValue()))
        }
    }
    @Test
    fun `Check size of viewModel recipes`() {
        runBlocking {
            mainViewModel.getRecipes()
            val result = mainViewModel.recipesList.getOrAwaitValue()
            assertThat(result.size, `is`(15))
        }
    }

    @Test
    fun `Check info essential`() {
        runBlocking {
            val result = service.getRecipes()
            for (recipe in result) {
                assertTrue(recipe.description.isNotEmpty())
                assertTrue(recipe.name.isNotEmpty())
                assertTrue(recipe.latitude.isNotEmpty())
                assertTrue(recipe.longitude.isNotEmpty())
            }
        }
    }

    @Test
    fun `Compare info essential in remote and local`() {
        runBlocking {
            val remoteResult = service.getRecipes()
            val localResult = JSONFileLoader().loadRecipesEntities("recipes_response_success.json")
            assertThat(remoteResult.size, `is`(localResult.size))
            remoteResult.forEachIndexed { index, remoteRecipe ->
                assertThat(remoteRecipe.name, `is`(localResult[index].name))
                assertThat(remoteRecipe.description, `is`(localResult[index].description))
                assertThat(remoteRecipe.latitude, `is`(localResult[index].latitude))
                assertThat(remoteRecipe.longitude, `is`(localResult[index].longitude))
            }
        }
    }
}