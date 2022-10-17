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


////@TypeConverters(Converters::class)
////@Entity(tableName = "product_small_image_urls")
//data class ProductSmallImageUrls(
//    @PrimaryKey
//    val productId: String,
//    val string: Array<String>
//){
//
//    constructor(): this(
//        "",
//        arrayOf()
//    )
//}

@Entity(tableName = "popular_new_products")
data class Product(

    val modificationDate: String,

    @SerializedName("app_sale_price")
    val appSalePrice: Double,

    val _id: String,

    @SerializedName("commission_rate")
    val commissionRate: Int,

    @SerializedName("hot_product_commission_rate")
    val hotProductCommissionRate: Float,

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

    @SerializedName("product_main_image_url")
    val productMainImageUrl: String,


//    @SerializedName("product_small_image_urls")
//    val productSmallImageUrls: ProductSmallImageUrls,
//    val productSmallImageUrls: String,

    @SerializedName("lastest_volume")
    val lastestVolume: Int,

    val wishedCount: Int,

    @SerializedName("metadata")
    val metadata: String

) {
    constructor(): this(
        "",
        0.0,
        "",
        0,
        0F,
        "",
        0,
        "",
//        arrayListOf(),
//        listOf(),
//        listOf(),
//        object: ,
//        object: ,
//        arrayListOf(),
        0,
        0,
        0,
        "",
        "",
        "",
        "",
        "",
//        ProductSmallImageUrls(),
        0,
        0,
        "",
    )
}








