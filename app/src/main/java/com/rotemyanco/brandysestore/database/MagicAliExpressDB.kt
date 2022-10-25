package com.rotemyanco.brandysestore.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rotemyanco.brandysestore.models.BaseProduct
import com.rotemyanco.brandysestore.models.Category



@Database(entities = [Category::class, BaseProduct::class], version = 24)
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
                    // there are 3 ways to declare the converter class ----> JUST ONE! more than 1 annotation - ERROR
//                .addTypeConverter(Converters::class)
                .fallbackToDestructiveMigration()
                .build()
        }
    }



}
