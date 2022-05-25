package com.example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.search_with_edit_textview.HomeSearchActivity
import com.example.search_with_searchview.DetailsActivity
import com.example.search_with_searchview.SearchActivity
import com.example.shardprf.R
import com.example.shardprf.databinding.ActivityStarterBinding

class StarterActivity : AppCompatActivity() {
    private lateinit var binding:ActivityStarterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStarterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClicks()
    }

    private fun onClicks() {
        binding.btnSearchEditText.setOnClickListener {
            val intent = Intent(this, HomeSearchActivity::class.java)
            startActivity(intent)
        }

        binding.btnSearchSearchView.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }
}