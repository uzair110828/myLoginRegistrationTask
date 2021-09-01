package com.example.loginandregistertask.model

import com.google.gson.annotations.SerializedName

data class mModel (@SerializedName("success")
                   val success: Boolean,
                   @SerializedName("status")
                   val status: Int,
                   @SerializedName("message")
                   val message: String,
                   @SerializedName("data")
                   val data:mdata,){
}