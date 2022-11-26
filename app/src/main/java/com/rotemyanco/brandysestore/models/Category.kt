package com.rotemyanco.brandysestore.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "product_categories")
data class Category(

	val modificationDate: String,

	@PrimaryKey
	@SerializedName("_id")
	val id: Int,

	@SerializedName("category_name")
	val categoryName: String,

	@SerializedName("api_category_id")
	val apiCategoryId: Int,

	val provider: String,

	@SerializedName("alie_parent_id")
	val alieParentIid: Int? = null,

	@SerializedName("api_parent_id")
	val apiParentId: Int,

	@SerializedName("alie_category_id")
	val alieCategoryId: Int? = null,

	@SerializedName("alie_category_name")
	val alieCategoryName: String? = null,

	@SerializedName("_v")
	val v: Int
) {
	override fun toString(): String = categoryName
}
