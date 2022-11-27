package com.rotemyanco.brandysestore.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "base_product")
data class BaseProduct(

	var modificationDate: String,

	var _id: String,

	@SerializedName("app_sale_price")
	var appSalePrice: Double,

	@SerializedName("app_sale_price_currency")
	var appSalePriceCurrency: String,

	@SerializedName("commission_rate")
	var commissionRate: String,

	var discount: String,

	@SerializedName("evaluate_rate")
	var evaluateRate: String,

	@SerializedName("first_level_category_id")
	var firstLevelCategoryId: Int,

	@SerializedName("first_level_category_name")
	var firstLevelCategoryName: String,

	@SerializedName("hot_product_commission_rate")
	var hotProductCommissionRate: String,

	@SerializedName("lastest_volume")
	var lastestVolume: Int,

	@SerializedName("original_price")
	var originalPrice: String,

	@SerializedName("original_price_currency")
	var originalPriceCurrency: String,

	@SerializedName("product_detail_url")
	var productDetailUrl: String,

	@SerializedName("product_id")
	@PrimaryKey
	var productId: String,

	@SerializedName("product_main_image_url")
	var productMainImageUrl: String,

	@SerializedName("product_title")
	var productTitle: String,

	@SerializedName("promotion_link")
	var promotionLink: String,

	@SerializedName("relevant_market_commission_rate")
	var relevantMarketCommissionRate: String,

	@SerializedName("sale_price")
	var salePrice: String,

	@SerializedName("sale_price_currency")
	var salePriceCurrency: String,

	@SerializedName("second_level_category_id")
	var secondLevelCategoryId: Int,

	@SerializedName("second_level_category_name")
	var secondLevelCategoryName: String,

	@SerializedName("shop_id")
	var shopId: Int,

	@SerializedName("shop_url")
	var shopUrl: String,

	@SerializedName("target_app_sale_price")
	var targetAppSalePrice: String,

	@SerializedName("target_app_sale_price_currency")
	var targetAppSalePriceCurrency: String,

	@SerializedName("target_original_price")
	var targetOriginalPrice: String,

	@SerializedName("target_original_price_currency")
	var targetOriginalPriceCurrency: String,

	@SerializedName("target_sale_price")
	var targetSalePrice: String,

	@SerializedName("target_sale_price_currency")
	var targetSalePriceCurrency: String,

	@SerializedName("discount_rate")
	var discountRate: Double,


	@SerializedName("shop_name")
	var shopName: String,

	var wishedCount: Int
) : Serializable {
	constructor() : this(
		"",
		"",
		0.0,
		"",
		"",
		"",
		"",
		0,
		"",
		"",
		0,
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		0,
		"",
		0,
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		0.0,
		"",
		0
	)
}

data class BaseProductResponse(
	val docs: List<BaseProduct>,
	val limit: Int,
	val page: Int,

	val hasPrevPage: Boolean,
	val hasNextPage: Boolean,

	val nextPage: Int,
	val prevPage: Int? = null,

	val totalPages: Int,
	@SerializedName("total_record_count")
	val totalRecordCount: Int,
)