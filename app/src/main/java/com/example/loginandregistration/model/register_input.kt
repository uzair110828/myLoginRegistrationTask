package com.example.loginandregistertask.model

import com.google.gson.annotations.SerializedName

data class register_input(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data:data,

    )
