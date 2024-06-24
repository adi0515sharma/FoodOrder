package com.kft.foodorder.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import java.util.concurrent.Executors
import kotlin.concurrent.Volatile




@Database(entities = [FoodDB::class], version = 1, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDBDao(): FoodDBDao?

    companion object {
        @Volatile
        private var INSTANCE: FoodDatabase? = null
        fun getInstance(context: Context): FoodDatabase? {
            if (INSTANCE == null) {
                synchronized(FoodDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = databaseBuilder(
                            context.applicationContext,
                            FoodDatabase::class.java, "FoodDatabase"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}