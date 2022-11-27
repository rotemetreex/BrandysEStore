package com.rotemyanco.brandysestore.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.rotemyanco.brandysestore.models.BaseProduct


@Dao
interface ProductDao {

	// 1) Upsert
	@Insert(onConflict = REPLACE)
	suspend fun upsertPopularProductsListSortedByNewest(popularProducts: List<BaseProduct>)

	// 2) Get all from bestSales(=most popular)/newest
	@Query("SELECT * FROM base_product")
	suspend fun getAllPopularNewArrivals(): List<BaseProduct>

	// 4) get popular Product By Id
	@Query("SELECT * FROM base_product WHERE productId IN (:productId)")
	suspend fun getPopularProductById(productId: String): BaseProduct

	// 5) upsert popular product by id
	@Insert(onConflict = REPLACE)
	suspend fun upsertBaseProduct(baseProduct: BaseProduct)

}