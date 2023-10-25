package com.fikrisandi.framework.network


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class  DataState<T>(
    @SerializedName("page")
    @Expose
    var page: Int? = null,
    @SerializedName("results")
    @Expose
    var results: T? = null,
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null,
    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null
)