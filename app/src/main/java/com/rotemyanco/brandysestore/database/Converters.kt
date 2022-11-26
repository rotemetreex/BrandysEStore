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

	@TypeConverter
	fun stringToInt(str: String): Int = str.toInt()

	@TypeConverter
	fun intToString(i: Int): String = i.toString()

	@TypeConverter
	fun arrayToString(array: Array<String>): String = array.toString()

	@TypeConverter
	fun stringToArray(str: String): Array<String> {
		val arrayType = object : TypeToken<Array<String>>() {}.type
		return Gson().fromJson(str, arrayType)
	}
}