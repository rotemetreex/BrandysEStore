package com.rotemyanco.brandysestore.database.productCategoriesDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rotemyanco.brandysestore.database.Converters
import com.rotemyanco.brandysestore.models.Category


@Database(entities = [Category::class], version = 1)
@TypeConverters(Converters::class)
abstract class CategoryDatabase: RoomDatabase() {


    abstract fun dao(): CategoryDao

    companion object {
        fun create(context: Context): CategoryDatabase {
            return Room.databaseBuilder(
                context,
                CategoryDatabase::class.java,
                "PRODUCTS_CATEGORY_DATABASE"
            ).build()
        }
    }


}