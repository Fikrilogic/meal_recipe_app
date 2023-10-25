package com.fikrisandi.framework.room.converter

interface BaseConverter<T> {

    fun objToString(data: T): String
    fun stringToObj(data: String):T
}