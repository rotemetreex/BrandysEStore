package com.rotemyanco.brandysestore.database.productsDB

import android.content.Context
import androidx.room.*
import com.rotemyanco.brandysestore.database.Converters
import com.rotemyanco.brandysestore.models.Product




@Database(entities = [Product::class], version = 1)
@TypeConverters(Converters::class)
abstract class ProductDatabase: RoomDatabase() {

    abstract fun dao(): ProductDao

    companion object {

        fun create(context: Context): ProductDatabase {
            return Room.databaseBuilder(
                context,
                ProductDatabase::class.java,
                "PRODUCTS_DATABASE"
            ).build()
        }
    }


}