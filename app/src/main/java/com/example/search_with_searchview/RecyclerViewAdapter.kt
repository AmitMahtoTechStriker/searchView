package com.example.search_with_searchview

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.shardprf.databinding.ListItemCountryBinding
import java.util.*
import kotlin.collections.ArrayList

class RecyclerViewAdapter(private var countryList: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var countryFilterList = ArrayList<String>()

    lateinit var mContext: Context
    var selectedPosition = -1

    class CountryHolder(var viewBinding: ListItemCountryBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

    init {
        countryFilterList = countryList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ListItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val sch = CountryHolder(binding)
        mContext = parent.context
        return sch
    }

    override fun getItemCount(): Int {
        return countryFilterList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val countryHolder = holder as CountryHolder
        countryHolder.viewBinding.selectCountryContainer.setBackgroundColor(Color.TRANSPARENT)

        countryHolder.viewBinding.selectCountryText.setTextColor(Color.BLACK)
        countryHolder.viewBinding.selectCountryText.text = countryFilterList[position]

        holder.viewBinding.radioButton.setChecked(selectedPosition == position)

        holder.viewBinding.radioButton.setOnClickListener {
            selectedPosition = position
            Toast.makeText(mContext, countryFilterList[position], Toast.LENGTH_SHORT).show()
            notifyDataSetChanged()
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, DetailsActivity::class.java)
            intent.putExtra("passselectedcountry", countryFilterList[position])
            mContext.startActivity(intent)
            Log.d("Selected:", countryFilterList[position])
        }
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    countryFilterList = countryList
                } else {
                    val resultList = ArrayList<String>()
                    for (row in countryList) {
                        if (row.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    countryFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = countryFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countryFilterList = results?.values as ArrayList<String>
                notifyDataSetChanged()
            }

        }
    }

}