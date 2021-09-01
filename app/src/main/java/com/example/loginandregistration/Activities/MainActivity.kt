package com.example.loginandregistertask.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.example.loginandregistertask.R
import com.example.loginandregistertask.Viewmodel.MainViewmodel
import com.example.loginandregistertask.Viewmodel.mViewModelFactory
import com.example.loginandregistertask.api.Repository
import com.example.loginandregistertask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val repository = Repository()
    val viewModelFactory = mViewModelFactory(repository)
    private lateinit var awesomeValidation: AwesomeValidation
    private lateinit var mViewmodel: MainViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mViewmodel = ViewModelProvider(this, viewModelFactory)
            .get(MainViewmodel::class.java)
        awesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        awesomeValidation.addValidation(this, R.id.edtEmailL, Patterns.EMAIL_ADDRESS, R.string.email)
        awesomeValidation.addValidation(this, R.id.edtpassL, ".{6,}", R.string.pass)

        binding.btnSignIn.setOnClickListener {
            if (awesomeValidation.validate()) {
                val email = binding.edtEmailL.text.toString().trim()
                val password = binding.edtpassL.editText?.text.toString()
                mViewmodel.signIn(
                    email, password
                )
                mViewmodel.signResponse.observe(this, Observer {
                    if (it.isSuccessful) {
                        Log.i(
                            "tag", "onCreate: ${it.body()?.status} ${it.message()}" +
                                    "  \" ${it.body()?.success} ${it.body()?.message}\")\n" +
                                    "${it.body()?.data} pass ${it.body()?.data?.password}" +
                                    " ${it.message()} ${it.code()}"
                        )
                        if (it.body()?.status == 500){
                            Log.i("tag", "onCreate: Login Failed ${it.body()!!.message}")
                            popupMessage("Login Failed ${it.body()!!.message}")
                        }
                        if (it.body()?.status == 406) {
                            popupMessage("Login Successful")
                            Log.i("tag", "onCreate: Login Successful")
                        } else if (it.body()?.data?.password == null) {
                            Log.i("tag", "onCreate: Login Successful null")
                        } else {
                            Log.i("tag", "onCreate: login failed")
                        }
                    } else {
                        Log.i("tag", "onCreate: ${it.message()} ${it.code()}")

                    }
                })
            } else {
                Toast.makeText(this, "Validation Failed", Toast.LENGTH_SHORT).show()
            }
        }





        binding.txtForgot.setOnClickListener {
            val intent = Intent(this, register::class.java)
            startActivity(intent)
        }
    }
    fun popupMessage(msg: String) {
        val builder1 = AlertDialog.Builder(this)
        builder1.setMessage(msg)
        builder1.setCancelable(true)

        builder1.setPositiveButton(
            "Done"
        ) { dialog, id -> dialog.cancel() }

        val alert11 = builder1.create()
        alert11.show()
    }
}