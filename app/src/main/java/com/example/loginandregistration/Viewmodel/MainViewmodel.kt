package com.example.loginandregistertask.Viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginandregistertask.api.Repository
import com.example.loginandregistertask.model.mModel
import com.example.loginandregistertask.model.register_input
import kotlinx.coroutines.launch
import retrofit2.Response


class MainViewmodel(private val repository: Repository) : ViewModel() {
    var myResponse: MutableLiveData<Response<register_input>> = MutableLiveData()
    var signResponse:MutableLiveData<Response<mModel>> = MutableLiveData()
    fun postData(email:String,password:String,first_name:String){
        viewModelScope.launch {
            val response = repository.sendData(email,password,first_name)
            myResponse.value = response
        }
    }
    fun signIn(email: String, password: String){
        viewModelScope.launch {
            val mresponse = repository.signIn(email,password)
            signResponse.value= mresponse
        }
    }


}