package com.anangkur.beritaku.app.data.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("data")
    val data: T,
    @SerializedName("message")
    val message: String? = ""
)