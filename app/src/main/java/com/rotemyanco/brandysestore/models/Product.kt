package com.rotemyanco.brandysestore.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName




data class ProductResponse(
    val docs: List<Product>,
    val totalDocs: Int,
    val limit: Int,
    val page: Int,
    val totalPages:Int,
    val pagingCounter: Int,
    val hasPrevPage: Boolean,
    val hasNextPage: Boolean,
    val prevPage: Int? = null,
    val nextPage: Int
)

@Entity
data class Product(

    @SerializedName("app_sale_price")
    val appSalePrice: Int,

    @SerializedName("app_sale_price_currency")
    val appSalePriceCurrency: String,

    @SerializedName("discount_rate")
    val discountRate: Int,

    @SerializedName("evaluate_rate")
    val evaluateRate: String,

    @SerializedName("first_level_category_id")
    val firstLevelCategoryId: Int,

    @SerializedName("second_level_category_id")
    val secondLevelCategoryId: Int,

//    val skuProperties: Array<String>,
//    val skuList: List<String>,
//    val specs: List<String>,
//    val quantityObject: Objects,
//    val feedBackRating: Objects,
//    val productCategoriesBreadcrumb: Array<String>,

    @SerializedName("shop_id")
    val shopId: Int,

    @SerializedName("shop_url")
    val shopUrl: String,

    @SerializedName("product_id")
    @PrimaryKey
    val productId: String,

    @SerializedName("shop_name")
    val shopName: String,

    @SerializedName("product_detail_url")
    val productDetailUrl: String,

//    @SerializedName("product_small_image_urls")
//    val productSmallImageUrls: Objects,

    @SerializedName("lastest_volume")
    val lastestVolume: Int,

    val wishedCount: Int,

//    @SerializedName("metadata")
//    val metadata: Metadata

) {
    constructor(): this(
        0,
        "",
        0,
        "",
        0,
        0,
//        arrayListOf(),
//        listOf(),
//        listOf(),
//        object: ,
//        object: ,
//        arrayListOf(),
        0,
        "",
        "",
        "",
        "",
//        object,
        0,
        0,
//        Metadata()
    )
}







