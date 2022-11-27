package com.rotemyanco.brandysestore.retrofit

import android.content.Context
import android.util.Log
import com.rotemyanco.brandysestore.database.*
import com.rotemyanco.brandysestore.models.BaseProduct
import com.rotemyanco.brandysestore.models.BaseProductResponse
import com.rotemyanco.brandysestore.models.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*


class MagicAliExpressAPIRepo(
	private val context: Context,
	private val catDao: CategoryDao,
	private val productDao: ProductDao,
	private val service: MagicAliExpressAPIService
) {

	private val logTag = "MagicAliExpressAPIRepo"
	private var mCatList = mutableListOf<Category>()


	suspend fun getAllProductCategories(): List<Category> {
		return withContext(Dispatchers.IO) {
			val calendar = Calendar.getInstance()
			val currentDate = calendar.time
			val sharedPrefs = context.getSharedPreferences("MAGIC_ALI_FILE", Context.MODE_PRIVATE)
			val nextUpdate = sharedPrefs.getLong("next_update_for_category_table", 0)
			val nextUpdateTime = Date(nextUpdate)
			val compare = currentDate.compareTo(nextUpdateTime)

			val result = when {
				compare >= 0 -> {
					service.getAllProductCategories()
						.onSuccess {
							mCatList.addAll(it)
							Log.d(logTag, "getAllProductCategories:    -----> mCatList  is populated:  mCatList.size() = ${mCatList.size}")
							catDao.upsert(mCatList)
							return@withContext it
						}
						.onFailure {
							val errorMessage = it.localizedMessage
							Log.d(logTag, "getAllProductCategories:    onFailure    --->   ***FAILURE!!***    --    error msg: $errorMessage")
							return@withContext mCatList
						}
					mCatList
				}
				else -> {
					catDao.getAll()
				}
			}
			return@withContext result
		}
	}

	suspend fun getProductListSortedByPopularNewArrival(): List<BaseProduct> {
		return withContext(Dispatchers.IO) {

			val calendar = Calendar.getInstance()
			val currentDate = calendar.time
			val sharedPrefs = context.getSharedPreferences("MAGIC_ALI_FILE", Context.MODE_PRIVATE)
			val nextUpdate =
				sharedPrefs.getLong("next_update_for_popular_product_table_sort_type_newest", 0)
			val nextUpdateTime = Date(nextUpdate)
			val cmpr = currentDate.compareTo(nextUpdateTime)

			val result = when {
				cmpr >= 0 -> {

					val popularProductListSortedByNewest =
						service.getAllBestSalesProductsSortedByNewest()
					productDao.upsertPopularProductsListSortedByNewest(
						popularProductListSortedByNewest
					)
					calendar.add(Calendar.WEEK_OF_MONTH, 1)

					with(sharedPrefs.edit()) {
						putLong(
							"next_update_for_popular_product_table_sort_type_newest",
							calendar.time.time
						)
						apply()
					}

					popularProductListSortedByNewest
				}
				else -> {
					productDao.getAllPopularNewArrivals()
				}
			}
			return@withContext result
		}
	}

	suspend fun getAllProductsByCategoryId(id: String): BaseProductResponse {
		return withContext(Dispatchers.IO) {
			return@withContext service.getAllProductsByCategoryId(id)
		}
	}

	suspend fun getBaseProductById(id: String): BaseProduct {
		return withContext(Dispatchers.IO) {
			return@withContext service.getProductById(id)
		}
	}

}
