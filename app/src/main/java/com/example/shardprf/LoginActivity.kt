package com.example.shardprf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.shardprf.application_class.ApplicationClass
import com.example.shardprf.databinding.ActivityLoginBinding
import com.example.shardprf.databinding.ActivityRegisterBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var email:String
    private lateinit var password:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClicks()
    }

    private fun onClicks() {
        binding.btnLogin.setOnClickListener {
            email =binding.txtEmail.text.toString().trim()
            password =binding.txtPassword.text.toString().trim()
            if (email.isEmpty()){
                Toast.makeText(this, "Enter you email first!", Toast.LENGTH_SHORT).show()
            }else if (password.isEmpty()){
                Toast.makeText(this, "Enter you password.", Toast.LENGTH_SHORT).show()
            }else{
                loginUser()
            }
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser() {
        ApplicationClass.getAppPreference().saveString("email",email)
        ApplicationClass.getAppPreference().saveString("password",password)
        // save login/ register status and manages this value in splash screen(save true/ faulse value take care)
        ApplicationClass.getAppPreference().login(this,true)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}