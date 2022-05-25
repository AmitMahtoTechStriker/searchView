package com.example.search_with_edit_textview

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.shardprf.databinding.ActivityHomeSearchBinding
import java.util.*


class HomeSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeSearchBinding
    private lateinit var adapter: ExampleAdapter
    private lateinit var dataList: DataModel
    private val countryListWithEmojis = ArrayList<DataModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getListOfCountries()
        searchData()

    }

    private fun searchData() {
        binding.etSearchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }
            override fun afterTextChanged(s: Editable) {
                filterData(s.toString())
            }
        })
    }

    // filter data with text
    private fun filterData(text: String) {
        val filteredList: ArrayList<DataModel> = ArrayList()

        for (name in countryListWithEmojis) {
            if (name.countryName.lowercase(Locale.ROOT).contains(text.lowercase(Locale.ROOT))) {
                // add new data after filter list
                filteredList.add(name)
            }
        }

        // call adapter method and direct pass new filtered list
        adapter.filterList(filteredList)
    }

    // this is fir get country list from Locale and
    private fun getListOfCountries() {
        val isoCountryCodes = Locale.getISOCountries()
        for (countryCode in isoCountryCodes) {
            val locale = Locale("", countryCode)
            val countryName = locale.displayCountry
            val flagOffset = 0x1F1E6
            val asciiOffset = 0x41
            val firstChar = Character.codePointAt(countryCode, 0) - asciiOffset + flagOffset
            val secondChar = Character.codePointAt(countryCode, 1) - asciiOffset + flagOffset
            val flag =
                (String(Character.toChars(firstChar)) + String(Character.toChars(secondChar)))

            // set data in modelClass and add in adapter
            countryListWithEmojis.add(DataModel("$countryName $flag"))
        }
        adapter = ExampleAdapter(countryListWithEmojis)
        binding.recyclerView.adapter = adapter

    }
}
