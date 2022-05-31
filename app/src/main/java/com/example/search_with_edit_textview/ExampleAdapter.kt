package com.example.search_with_edit_textview

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.search_with_searchview.DetailsActivity
import com.example.shardprf.databinding.ListItemCountryBinding


class ExampleAdapter(countryList: ArrayList<DataModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var countryFilterList = ArrayList<DataModel>()

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
        countryHolder.viewBinding.selectCountryText.text = countryFilterList[position].countryName

        holder.viewBinding.radioButton.setChecked(selectedPosition == position)

        holder.viewBinding.radioButton.setOnClickListener {
            selectedPosition = position
            Toast.makeText(mContext, countryFilterList[position].countryName, Toast.LENGTH_SHORT).show()
            notifyDataSetChanged()
        }


        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, DetailsActivity::class.java)
            intent.putExtra("passselectedcountry", countryFilterList[position].countryName)
            mContext.startActivity(intent)
            Log.d("Selected:", countryFilterList[position].countryName)
        }

//        countryHolder.viewBinding.radioButton.setOnCheckedChangeListener(
//            CompoundButton.OnCheckedChangeListener { compoundButton, b ->
//                // check condition
//                if (b) {
//                    // When checked
//                    // update selected position
//                    selectedPosition = position
//
//
//                }
//            })
//
//        countryHolder.viewBinding.radioButton.setChecked(position == selectedPosition);

    }

    // in this list data come from new filter list
    fun filterList(filteredList: ArrayList<DataModel>) {
        // set data main List
        countryFilterList = filteredList
        notifyDataSetChanged()
    }

}