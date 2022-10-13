package com.rotemyanco.brandysestore.retrofit

import android.content.Context
import android.util.Log
import com.rotemyanco.brandysestore.database.productCategoriesDB.CategoryDatabase
import com.rotemyanco.brandysestore.models.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date


class CategoriesRepo {


    companion object {

        private const val TAG = "CategoriesRepo"

        suspend fun getAllProductCategories(context: Context): List<Category> {
            return withContext(Dispatchers.IO) {

                val calendar = Calendar.getInstance()
                val currentDate = calendar.time

                val sharedPrefs =
                    context.getSharedPreferences("MAGIC_ALI_PRODUCTS_FILE", Context.MODE_PRIVATE)
                val nextUpdate = sharedPrefs.getLong("next_update", 0)

                val nextUpdateTime = Date(nextUpdate)
                val compare = currentDate.compareTo(nextUpdateTime)

                val catDB = CategoryDatabase.create(context).dao()

                val result = when {
                    compare >= 0 -> {
                        // server
                        val categories =
                            MagicAliExpressAPIService.create().getAllProductCategories()
                        catDB.upsert(categories)
                        calendar.add(Calendar.WEEK_OF_MONTH, 1)

                        with(sharedPrefs.edit()) {
                            putLong("next_update", calendar.time.time)
                            apply()
                        }

                        categories
                    }
                    else -> {

                        // LocalDB
                        Log.d(
                            TAG,
                            "getAllProductCategories:  -----> catDB.getAll()  -----> ${catDB.getAll()}"
                        )
                        catDB.getAll()
                    }
                }
                Log.d(TAG, "getAllProductCategories:       ------> result ---> $result")
                return@withContext result
            }
        }

    }


}



