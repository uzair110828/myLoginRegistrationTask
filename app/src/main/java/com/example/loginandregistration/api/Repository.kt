package com.example.loginandregistertask.api

import com.example.loginandregistertask.model.mModel
import com.example.loginandregistertask.model.register_input
import retrofit2.Response

class Repository {

    suspend fun sendData(
        email:String, password: String, first_name:String):Response<register_input>{
        return RetrofitInstance.api.signUpData(email,password,first_name,"users",
            "M", "01/01/2010","+91","6565656565",
        "","A","A","1.0","11","A10","1")
    }

    suspend fun signIn(
        email:String, password: String):Response<mModel>{
        return RetrofitInstance.api.signIn(email,password)
    }

}