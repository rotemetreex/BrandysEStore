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

//    @SerializedName("product_small_image_urls")
//    open var productSmallImageUrls: ProductSmallImageUrls,

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

//     var skuProperties: SkuProperties,

//     var skuList: SkuList,

//     var specs: Specs,

//     var quantityObject: QuantityObject,

//     var feedBackRating: FeedBackRating,

//     var productCategoriesBreadcrumb: Array<ProductCategoriesBreadcrumb>,

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
//        ProductSmallImageUrls(arrayOf()),
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
//        SkuProperties(),
//        SkuList(),
//        Specs(),
//        QuantityObject(),
//        FeedBackRating(),
//        arrayOf(),
        "",
        0
    )
}

data class BaseProductResponse(
    val docs: List<BaseProduct>,
    val searchEngine: SearchEngine,
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

data class SearchEngine(
    val category_id: String,
    val keywords: String,
    val page: Int,
    val isFastShipping: Boolean,
    val isFreeShipping: Boolean,
    val isPopular: Boolean,
    val target_currency: String,
    val shipToCountry: String,
    val sortType: String,
    val sortOrder: String
)

data class SkuProperties(
    val isShowTypeColor: Boolean,
    val order: Int,
    val showType: String,
    val showTypeColor: Boolean,
    val skuPropertyId: Int,
    val skuPropertyName: String,
    val skuPropertyValues: Array<SkuPropertyValues> = arrayOf()
) {
    constructor() : this(
        false,
        0,
        "",
        false,
        0,
        "",
        arrayOf()
    )
}

data class SkuList(
    val freightExt: String,
    val skuAttr: String,
    val skuId: Long,
    val skuIdStr: String,
    val skuPropIds: String,
    val skuVal: SkuVal,
) {
    constructor() : this(
        "",
        "",
        0L,
        "",
        "",
        SkuVal()
    )
}


data class SkuPropertyValues(
    val propertyValueDefinitionName: String,
    val propertyValueDisplayName: String,
    val propertyValueId: Int,
    val propertyValueIdLong: Int,
    val propertyValueName: String,
    val skuColorValue: String,
    val skuPropertyImagePath: String,
    val skuPropertyImageSummPath: String,
    val skuPropertyTips: String,
    val skuPropertyValueShowOrder: Int,
    val skuPropertyValueTips: String
)

data class SkuAmount(
    val currency: String,
    val formatedAmount: String,
    val value: Double
) {
    constructor() : this(
        "",
        "",
        0.0
    )
}

data class SkuActivityAmount(
    val currency: String,
    val formatedAmount: String,
    val value: Double
) {
    constructor() : this(
        "",
        "",
        0.0
    )
}


data class SkuVal(
    val availQuantity: Int,
    val discount: String,
    val inventory: Int,
    val isActivity: Boolean,
    val optionalWarrantyPrice: Array<String>?,
    val skuActivityAmount: SkuActivityAmount,
    val skuAmount: SkuAmount,
    val skuCalPrice: String,
) {
    constructor() : this(
        0,
        "",
        0,
        false,
        arrayOf(),
        SkuActivityAmount(),
        SkuAmount(),
        ""
    )
}

data class Specs(
    val attrName: String,
    val attrValue: String
) {
    constructor() : this(
        "",
        ""
    )
}

// unkown object!!!!
data class Features(
    val att: String
) {
    constructor() : this(
        ""
    )
}

data class QuantityObject(
    val activity: Boolean,
    val displayBulkInfo: Boolean,
    val features: Features,
//    val i18nMap: Obji18nMap,
    val id: Int,
    val lot: Boolean,
    val multiUnitName: String,
    val name: String,
    val oddUnitName: String,
    val purchaseLimitNumMax: Int,
    val totalAvailQuantity: Int,
) {
    constructor() : this(
        false,
        false,
        Features(),
//        Obji18nMap(),
        0,
        false,
        "",
        "",
        "",
        0,
        0
    )
}

data class FeedBackRating(
    val averageStar: String,
    val averageStarRage: String,
    val display: Boolean,
    val evarageStar: String,
    val evarageStarRage: String,
    val fiveStarNum: Int,
    val fiveStarRate: String,
    val fourStarNum: Int,
    val fourStarRate: String,
    val oneStarNum: Int,
    val oneStarRate: String,
    val positiveRate: String,
    val threeStarNum: Int,
    val threeStarRate: String,
    val totalValidNum: Int,
    val trialReviewNum: Int,
    val twoStarNum: Int,
    val twoStarRate: String
) {
    constructor() : this(
        "",
        "",
        false,
        "",
        "",
        0,
        "",
        0,
        "",
        0,
        "",
        "",
        0,
        "",
        0,
        0,
        0,
        ""
    )
}

data class ProductCategoriesBreadcrumb(
    val cateId: Int,
    val name: String,
    val remark: String,
    val url: String
)

data class ProductSmallImageUrls(
    val string: Array<String>
)

//@Entity(tableName = "base_product_small_image_urls_with_product_id")
//data class BaseProductSmallImageUrlsWithProductId(
//    @Embedded val baseProduct: BaseProduct,
//    @Relation(
//        parentColumn = "product_small_image_urls",
//        entityColumn = "productId"
//    )
//    val smallImageUrls: Array<ProductSmallImageUrls>
//)


//    @SerializedName("metadata")
//    val metadata: BaseProductMetadata


//data class AePlusModule(
//    val features: Features,
//    val i18nMap: Obji18nMap,
//    val id: Int,
//    val name: String
//)

//data class ActionModule(
//    val addToCartUrl: String,
//    val aeOrderFrom: String,
//    val allowVisitorAddCart: Boolean,
//    val cartDetailUrl: String,
//    val categoryId: Int,
//    val comingSoon: Boolean,
//    val companyId: Int,
//    val confirmOrderUrl: String,
//    val features: Features,
//    val freightExt: String,
//    val i18nMap: Obji18nMap,
//)

//data class BaseProductMetadata(
//    val actionModule: ActionModule,
//    val aePlusModule: AePlusModule,
//    val buyerProtectionModule: BuyerProtectionModule,
//    val commonModule: CommonModule,
//    val couponModule: CouponModule,
//    val crossLinkModule: CrossLinkModule,
//    val descriptionModule: DescriptionModule,
//    val features: Features,
//    val feedbackModule: FeedbackModule,
//    val groupShareModule: GroupShareModule,
//    val i18nMap: Obji18nMap,
//    val imageModule: ImageModule,
//    val installmentModule: InstallmentModule,
//    val middleBannerModule: MiddleBannerModule,
//    val name: String,
//    val otherServiceModule: OtherServiceModule,
//    val pageModule: PageModule
//    val preSaleModule: PreSaleModule,
//    val prefix: String,
//    val priceModule: PriceModule,
//    val quantityModule: QuantityModule,
//    val recommendModule: RecommendModule,
//    val redirectModule: RedirectModule,
//    val shippingModule: ShippingModule,
//    val skuModule: SkuModule,
//    val specsModule: SpecsModule,
//    val storeModule: StoreModule,
//    val titleModule: TitleModule,
//    val webEnv: WebEnv
//)


//data class Obji18nMap(
//    val LOT: String,
//    val LOTS: String,
//    val BUY_LIMIT: String,
//    val QUANTITY: String,
//    val OFF_OR_MORE: String,
//    val ONLY_QUANTITY_LEFT: String,
//    val ADDTIONAL: String,
//    val QUANTITY_AVAILABLE: String
//)