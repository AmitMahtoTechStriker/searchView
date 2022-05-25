package com.example.shardprf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.shardprf.application_class.ApplicationClass
import com.example.shardprf.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding :ActivityRegisterBinding
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var userName:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClicks()
    }

    private fun onClicks() {
        binding.btnRegister.setOnClickListener {
            userName =binding.txtName.text.toString().trim()
            email =binding.txtEmail.text.toString().trim()
            password =binding.txtPassword.text.toString().trim()
            if (userName.isEmpty()){
                Toast.makeText(this, "enter you username", Toast.LENGTH_SHORT).show()
            }else if (email.isEmpty()){
                Toast.makeText(this, "enter you email first!", Toast.LENGTH_SHORT).show()
            }else if (password.isEmpty()){
                Toast.makeText(this, "enter you password.", Toast.LENGTH_SHORT).show()
            }else{
                registerUser()
            }
        }
    }

    private fun registerUser() {
        ApplicationClass.getAppPreference().saveString("username",userName)

        // save login/ register status and manages this value in splash screen
        ApplicationClass.getAppPreference().login(this,true)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}