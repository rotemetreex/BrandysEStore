package com.rotemyanco.brandysestore.database.productsDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.rotemyanco.brandysestore.models.Product


@Dao
interface ProductDao {

    // 1) Upsert
    @Insert(onConflict = REPLACE)
    suspend fun upsert(products: List<Product>)

    // 2) get all
    @Query("SELECT * FROM Product")
    suspend fun getAllMovies(): List<Product>

    // 3) get by id
    @Query("SELECT * FROM Product WHERE productId IN (:id)")
    suspend fun getProductById(id: Int): Product

    // 4) delete ?
    @Delete
    suspend fun delete(product: Product)
}