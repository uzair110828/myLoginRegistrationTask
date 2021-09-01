package com.example.loginandregistertask.model

import com.google.gson.annotations.SerializedName

class mdata(
    @SerializedName("email")
    val email:String,
    @SerializedName("password")
    val password:String,
    @SerializedName("device_type")
    val device_type:String,
    @SerializedName("device_token")
    val device_token:String,
    @SerializedName("app_version")
    val app_version:String,
    @SerializedName("os_version")
    val os_version:String,
    @SerializedName("device_model")
    val device_model:String
) {
}