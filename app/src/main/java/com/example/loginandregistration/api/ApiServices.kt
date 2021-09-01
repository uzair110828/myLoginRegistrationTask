package com.example.loginandregistertask.api

import com.example.loginandregistertask.model.mModel
import com.example.loginandregistertask.model.register_input
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {
//
    @FormUrlEncoded
    @POST("register")
    suspend fun signUpData(
        @Field("email") email:String,
        @Field("password") password:String,
        @Field("first_name") first_name:String,
        @Field("last_name") last_name:String,
        @Field("gender") gender:String,
        @Field("dob") dob:String,
        @Field("phone_code") phone_code:String,
        @Field("phone") phone:String,
        @Field("image") image:String,
        @Field("device_type") device_type:String,
        @Field("device_token") device_token:String,
        @Field("app_version") app_version:String,
        @Field("os_version") os_version:String,
        @Field("device_model") device_model:String,
        @Field("newsletter_subscribed") newsletter_subscribed:String,
    ): Response<register_input>

    @FormUrlEncoded
    @POST("register")
    suspend fun signIn(
        @Field("email") email:String,
        @Field("password") password:String,
    ): Response<mModel>


}