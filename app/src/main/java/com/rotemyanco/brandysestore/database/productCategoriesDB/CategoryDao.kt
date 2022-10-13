package com.rotemyanco.brandysestore.database.productCategoriesDB

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.rotemyanco.brandysestore.models.Category


@Dao
interface CategoryDao {

    // 1) Upsert
    @Insert(onConflict = REPLACE)
    suspend fun upsert(categories: List<Category>)

    // 2) get all
    @Query("SELECT * FROM product_categories")
    suspend fun getAll(): List<Category>

    // 3) get by id
    @Query("SELECT * FROM PRODUCT_CATEGORIES WHERE id IN (:id)")
    suspend fun getCategoryById(id: Int): Category

    // 4) delete ?
    @Delete
    suspend fun deleteCat(category: Category)
}