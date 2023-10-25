package com.fikrisandi.framework.extension

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

val gson: Gson = GsonBuilder().setLenient().setPrettyPrinting().disableHtmlEscaping().create()

inline fun <reified T> T.toJson(): String {
    return try {
        gson.toJson(this)
    } catch (e: Exception) {
        ""
    }
}

inline fun <reified T> String.fromJson(): T? {
    return try {
        gson.fromJson(this, T::class.java)
    } catch (e: Exception) {
        null
    }
}

inline fun <reified T> String.fromJsonList(): List<T>? {
    return try {
        val type = object : TypeToken<List<T>>() {}.type
        gson.fromJson(this, type)
    } catch (e: Exception) {
        null
    }
}

inline fun <reified T> T.toStringMap(): Map<String, String> {
    return try {
        val type = object : TypeToken<Map<String, String>>() {}.type
        gson.fromJson(this.toJson(), type)
    } catch (e: Exception) {
        emptyMap()
    }
}

inline fun <reified T> T.toMap(): Map<String, Any> {
    return try {
        val type = object : TypeToken<Map<String, Any>>() {}.type
        gson.fromJson(this.toJson(), type)
    } catch (e: Exception) {
        emptyMap()
    }
}