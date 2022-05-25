package com.example.shardprf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shardprf.application_class.ApplicationClass
import com.example.shardprf.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private var username:String=""
    private var email:String=""
    private var password:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        username= ApplicationClass.getAppPreference().getString("username").toString()
        email= ApplicationClass.getAppPreference().getString("email").toString()
        password= ApplicationClass.getAppPreference().getString("password").toString()

        setData()
        onClicks()
    }

    private fun onClicks() {
        binding.btnLogout.setOnClickListener {
            ApplicationClass.getAppPreference().logout()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setData() {
        binding.txtUsername.text=username
        binding.txtEmail.text=email
        binding.txtPassword.text=password
    }
}