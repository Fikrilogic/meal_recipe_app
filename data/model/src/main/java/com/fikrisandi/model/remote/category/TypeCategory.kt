package com.fikrisandi.model.remote.category

import com.google.gson.annotations.SerializedName

data class TypeCategory(
    @SerializedName("strCategory")
    val name: String
){
    override fun toString(): String {
        return this.name
    }
}

data class TypeCategoryResponse(
    @SerializedName("meals")
    val categories: List<TypeCategory>
)