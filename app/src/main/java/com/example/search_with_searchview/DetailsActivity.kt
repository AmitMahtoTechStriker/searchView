package com.example.search_with_searchview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shardprf.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.detailCountryText.text = intent.extras!!.getString("passselectedcountry")!!

    }
}