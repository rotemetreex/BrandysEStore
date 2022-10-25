package com.rotemyanco.brandysestore.retrofit

import com.rotemyanco.brandysestore.models.BaseProduct
import com.rotemyanco.brandysestore.models.BaseProductResponse
import com.rotemyanco.brandysestore.models.Category
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface MagicAliExpressAPIService {

    // product category List --> ALL
    @GET("api/v2/categories")
    suspend fun getAllProductCategories(): List<Category>

    // productList by Cat Id --> all products in category
    @GET("/api/category/{categoryId}/products")
    suspend fun getAllProductsByCategoryId(@Path(value = "categoryId") id: String): BaseProductResponse


    // best sales products --> ALL
    @GET("api/bestSales/products")
    suspend fun getAllBestSalesProducts(): List<BaseProduct>


    // best sales products --> SORTED by NEWEST
    @GET("api/bestSales/SortedByNewest")
    suspend fun getAllBestSalesProductsSortedByNewest(): List<BaseProduct>


    // popular product by id
    @GET("api/product/{productId}")
    suspend fun getProductById(@Path("productId") productId: String): BaseProduct





//    fun getById(id: Int): PopularProduct
//
//    fun upsert()


    companion object {
        private const val API_KEY = "3fed57fa81msh3a7355ae6f1c326p1e5a20jsnc855a7226638"
        private const val HOST_HEADER = "magic-aliexpress1.p.rapidapi.com"


        fun create(): MagicAliExpressAPIService {
            val okHttpClient = OkHttpClient.Builder()
            okHttpClient.addInterceptor { chain ->

                val originalRequest = chain.request()
                val requestWithHeaders = originalRequest.newBuilder()
                    .header("X-RapidAPI-Key", API_KEY)
                    .header("X-RapidAPI-Host", HOST_HEADER)
                    .method(originalRequest.method(), originalRequest.body())
                    .build()

                chain.proceed(requestWithHeaders)
            }
            val client = okHttpClient.build()

            return Retrofit.Builder()
                .baseUrl("https://magic-aliexpress1.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(MagicAliExpressAPIService::class.java)
        }
    }


}
















//            okHttpClient.addInterceptor(object: Interceptor {
//                override fun intercept(chain: Interceptor.Chain): Response {
//
//                    val originalRequest = chain.request()
//
//                    val requestWithHeaders = originalRequest.newBuilder()
//                        .header("X-RapidAPI-Key", API_KEY)
//                        .header("X-RapidAPI-Host", HOST_HEADER)
//                        .method(originalRequest.method(), originalRequest.body())
//                        .build()
//
//                    return chain.proceed(requestWithHeaders)
//                }
//            } )



