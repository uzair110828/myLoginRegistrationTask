package com.example.loginandregistertask.Activities

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import com.example.loginandregistertask.R
import com.example.loginandregistertask.Viewmodel.*
import com.example.loginandregistertask.api.Repository
import com.example.loginandregistertask.databinding.ActivityRegisterBinding


class register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    val repository = Repository()
    val viewModelFactory = mViewModelFactory(repository)
    private lateinit var awesomeValidation: AwesomeValidation
    private lateinit var mViewmodel: MainViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mViewmodel = ViewModelProvider(this, viewModelFactory)
            .get(MainViewmodel::class.java)
        awesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        text_check()

        binding.btnSignUp.setOnClickListener {
            binding.progress.visibility = View.VISIBLE
            if (awesomeValidation.validate()) {
                val name = binding.edtName.text.toString().trim()
                val email = binding.edtEmail.text.toString().trim()
                val password = binding.edtpass.editText?.text.toString()
               Log.i("tag", "onCreate: password $password")
                    mViewmodel.postData(
                        email, password , name
                    )
                mViewmodel.myResponse.observe(this, Observer {
                    if (it.isSuccessful) {
                        binding.progress.visibility = View.GONE

                        Log.i("tag", "onCreate: ${it.body()?.status}" + "  \" ${it.body()?.success} ${it.body()?.message}\")\n" + "${it.body()?.data} pass ${it.body()?.data?.password}" + " ${it.message()} ${it.code()}")

                        val builder1 = AlertDialog.Builder(this)
                        builder1.setMessage(it.body()?.message)
                        builder1.setCancelable(true)

                        builder1.setPositiveButton(
                            "Done"
                        ) { dialog, id -> dialog.dismiss()
                        }
                        val alert11 = builder1.create()
                        alert11.show()

                    } else {
                        Log.i("tag", "onCreate: ${it.message()} ${it.code()}")
                        popupMessage(it.message())
                        binding.progress.visibility = View.GONE
                    }
                })
            } else {
                Toast.makeText(this, "Validation Failed", Toast.LENGTH_SHORT).show()
                binding.progress.visibility = View.GONE
            }
        }
    }

    private fun text_check() {
        awesomeValidation.addValidation(
            this,
            R.id.edtName,
            RegexTemplate.NOT_EMPTY,
            R.string.fname
        )

        awesomeValidation.addValidation(this, R.id.edtEmail, Patterns.EMAIL_ADDRESS, R.string.email)
        awesomeValidation.addValidation(this, R.id.edtpass, ".{6,}", R.string.pass)
        awesomeValidation.addValidation(this, R.id.edtConpass, R.id.edtpass, R.string.confirm_pass)

    }
    fun popupMessage( msg: String) {
        val builder1 = AlertDialog.Builder(this)
        builder1.setMessage(msg)
        builder1.setCancelable(true)

        builder1.setPositiveButton(
            "Done"
        ) { dialog, id -> dialog.dismiss()
        }

        val alert11 = builder1.create()
        alert11.show()
    }
}