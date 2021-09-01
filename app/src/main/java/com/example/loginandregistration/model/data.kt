package com.example.loginandregistertask.model

import com.google.gson.annotations.SerializedName

data class data (
    @SerializedName("email")
    val email:String,
    @SerializedName("password")
    val password:String,
    @SerializedName("first_name")
    val first_name:String,
    @SerializedName("last_name")
    val last_name:String,
    @SerializedName("gender")
    val gender:String,
    @SerializedName("dob")
    val dob:String,
    @SerializedName("phone_code")
    val phone_code:String,
    @SerializedName("phone")
    val phone:String,
    @SerializedName("image")
    val image:String,
    @SerializedName("device_type")
    val device_type:String,
    @SerializedName("device_token")
    val device_token:String,
    @SerializedName("app_version")
    val app_version:String,
    @SerializedName("os_version")
    val os_version:String,
    @SerializedName("device_model")
    val device_model:String,
    @SerializedName("newsletter_subscribed")
    val newsletter_subscribed:String,
        )