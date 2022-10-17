package com.rotemyanco.brandysestore.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun listToString(list: List<Int>): String = list.toString()

    @TypeConverter
    fun stringToList(str: String): List<Int> {
        val stringType = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(str, stringType)
    }

//    @TypeConverter
//    fun arrayToString(array: Array<String>): String = array.toString()
//
//    @TypeConverter
//    fun stringToArray(str: String): Array<String> {
//        val arrayType = object : TypeToken<Array<String>>() {}.type
//        return Gson().fromJson(str, arrayType)
//    }

//    @TypeConverter
//    fun storedStringToMyObjects(data: String?): List<ProductSmallImageUrls?>? {
//        println("----------------- myObject:  fun   storedString   ToMyObjects()-----------------")
//        val gson = Gson()
//        if (data == null) {
//            return Collections.emptyList()
//        }
//        val listType: Type = object : TypeToken<List<ProductSmallImageUrls?>?>() {}.type
//        return gson.fromJson<List<ProductSmallImageUrls?>>(data, listType)
//    }
//
//
//    @TypeConverter
//    fun myObjectsToStoredString(myObject: ProductSmallImageUrls?): String? {
//        println("----------------- myObject:  fun    myObjects     ToStoredString()-----------------")
//        val gson = Gson()
//        if (myObject == null) {
////            return Collections.emptyList()
//            return null
//        }
//        return gson.toJson(myObject)
//    }
}