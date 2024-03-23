package com.fraporitmos.yapechallengue.dataAccess

import com.fraporitmos.yapechallengue.core.entities.Recipe
import com.google.gson.Gson
import java.io.InputStreamReader

class JSONFileLoader {
    private var jsonStr: String? = null

    fun loadJSONString(fileName: String): String? {
        val loader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(fileName))
        jsonStr = loader.readText()
        loader.close()
        return jsonStr

    }

    fun loadRecipesEntities(fileName: String): List<Recipe> {
        val loader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(fileName))
        jsonStr = loader.readText()
        loader.close()
        return Gson().fromJson(jsonStr, Array<Recipe>::class.java).toList()

    }
}