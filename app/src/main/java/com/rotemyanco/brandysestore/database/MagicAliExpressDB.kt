package com.rotemyanco.brandysestore.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rotemyanco.brandysestore.models.Category
import com.rotemyanco.brandysestore.models.Product


@Database(entities = [Category::class, Product::class], version = 16)
@TypeConverters(Converters::class)
abstract class MagicAliExpressDB : RoomDatabase() {

    abstract fun getProductDao(): ProductDao

    abstract fun getCategoryDao(): CategoryDao


    companion object {
        fun create(context: Context): MagicAliExpressDB {
            return Room.databaseBuilder(
                context,
                MagicAliExpressDB::class.java,
                "MAGIC_ALI_EXPRESS_DB"
            )
//                .addTypeConverter(Converters::class)
                .fallbackToDestructiveMigration()
                .build()
        }
    }



}
