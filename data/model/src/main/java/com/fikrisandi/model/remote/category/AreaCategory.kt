package com.fikrisandi.model.remote.category

import com.google.gson.annotations.SerializedName

data class AreaCategory(
    @SerializedName("strArea")
    val area: String
){
    override fun toString(): String {
        return this.area
    }
}


data class AreaCategoryResponse(
    @SerializedName("meals")
    val categories: List<AreaCategory>
)