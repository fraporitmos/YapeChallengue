package com.fraporitmos.yapechallengue.framework.persist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fraporitmos.yapechallengue.framework.persist.db.entities.RecipeEntity

@Database(
    entities = [RecipeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseService : RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "recipes.db"
        private var instance: DatabaseService? = null

        private fun create(context: Context): DatabaseService =
            Room.databaseBuilder(context, DatabaseService::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context): DatabaseService =
            (instance ?: create(context)).also { instance = it }

    }
    abstract fun getDao(): AppDao
}