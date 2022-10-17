package com.rotemyanco.brandysestore.retrofit

import android.content.Context
import android.util.Log
import com.rotemyanco.brandysestore.database.*
import com.rotemyanco.brandysestore.models.Category
import com.rotemyanco.brandysestore.models.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date


class MagicAliExpressAPIRepo(private val context: Context) {

    private val db: MagicAliExpressDB = MagicAliExpressDB.create(context)
    private val catDao: CategoryDao = db.getCategoryDao()
    private val  productDao: ProductDao = db.getProductDao()

    private val TAG = "MagicAliExpressAPIRepo"


        suspend fun getAllProductCategories(): List<Category> {
            return withContext(Dispatchers.IO) {
                val calendar = Calendar.getInstance()
                val currentDate = calendar.time
                val sharedPrefs =
                    context.getSharedPreferences("MAGIC_ALI_FILE", Context.MODE_PRIVATE)
                val nextUpdate = sharedPrefs.getLong("next_update_for_categories_table", 0)
                val nextUpdateTime = Date(nextUpdate)
                val compare = currentDate.compareTo(nextUpdateTime)

                val result = when {
                    compare >= 0 -> {
                        // server
                        val categories =
                            MagicAliExpressAPIService.create().getAllProductCategories()
                        catDao.upsert(categories)
                        calendar.add(Calendar.WEEK_OF_MONTH, 1)
                        with(sharedPrefs.edit()) {
                            putLong("next_update_for_categories_table", calendar.time.time)
                            apply()
                        }
                        categories
                    }
                    else -> {
                        // LocalDB
                        Log.d(TAG, "getAllProductCategories:  -----> catDB.getAll()  -----> ${catDao.getAll()}")
                        catDao.getAll()
                    }
                }
                Log.d(TAG, "getAllProductCategories:       ------> result ---> $result")
                return@withContext result
            }
        }



        suspend fun getProductListSortedByPopularNewArrival(): List<Product> {
            return withContext(Dispatchers.IO) {

                val calendar = Calendar.getInstance()
                val currentDate = calendar.time
                val sharedPrefs =
                    context.getSharedPreferences("MAGIC_ALI_FILE", Context.MODE_PRIVATE)
                val nextUpdate = sharedPrefs.getLong("next_update_for_popular_new_product_table", 0)
                val nextUpdateTime = Date(nextUpdate)
                val compare = currentDate.compareTo(nextUpdateTime)

                val result = when {
                    compare >= 0 -> {
                        // server
                        val popularNewProducts =
                            MagicAliExpressAPIService.create().getAllBestSalesProductsSortedByNewest()
                        productDao.upsert(popularNewProducts)
                        calendar.add(Calendar.WEEK_OF_MONTH, 1)

                        with(sharedPrefs.edit()) {
                            putLong("next_update_for_popular_new_product_table", calendar.time.time)
                            apply()
                        }
                        popularNewProducts
                    }
                    else -> {
                        // LocalDB
                        Log.d(
                            TAG,
                            "getProductListSortedByPopularNewArrival:  -----> catDao.getAll()  -----> ${productDao.getAllPopularNewArrivals()}"
                        )
                        productDao.getAllPopularNewArrivals()
                    }
                }
                Log.d(TAG, "getProductListSortedByPopularNewArrival:       ------> result ---> $result")
                return@withContext result
            }
        }


    }
















































//    suspend fun getSmallImageUrlsArrayByProductId(): Array<String> {
//        return withContext(Dispatchers.IO) {
//
//            val smallImageUrlsArray = MagicAliExpressAPIService.create().
//
//
//            return@withContext result
//        }
//    }




