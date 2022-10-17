package com.rotemyanco.brandysestore.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.rotemyanco.brandysestore.models.Product


@Dao
interface ProductDao {

    // 1) Upsert
    @Insert(onConflict = REPLACE)
    suspend fun upsert(products: List<Product>)

    @Query("SELECT * FROM popular_new_products")
    suspend fun getAllPopularNewArrivals(): List<Product>

//    @Query("SELECT * FROM PRODUCT_SMALL_IMAGE_URLS WHERE productId IN (:productId)")
//    suspend fun getSmallImageUrlsArrayByProductId(productId: String): Array<String>


//     2) get all products in cat id
//    @Query("SELECT * FROM ")

    // 2) get all products by Cat id
//    @Query("SELECT * FROM ")
//    suspend fun getAllProducts(): List<Product>

    // 3) get by id
//    @Query("SELECT * FROM Product WHERE productId IN (:id)")
//    suspend fun getProductById(id: Int): Product

    // 4) delete ?
//    @Delete
//    suspend fun delete(product: Product)
}