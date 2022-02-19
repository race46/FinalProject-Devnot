package com.example.finalproject_devnot.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject_devnot.R
import com.example.finalproject_devnot.databinding.SepetRecyclerItemBinding
import com.example.finalproject_devnot.entity.SepetYemekler
import com.example.finalproject_devnot.viewmodel.SepetViewModel

class SepetAdapter(val yemekler: ArrayList<SepetYemekler>,private val viewModel: SepetViewModel) :
    RecyclerView.Adapter<SepetAdapter.AdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AdapterHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.sepet_recycler_item, parent, false
            )
        )

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {
        holder.tasarim.yemek = yemekler[position]
        holder.tasarim.button.setOnClickListener{
            viewModel.yemekSil(yemekler[position].yemek_adi,position)
        }
    }



    override fun getItemCount() = yemekler.size
    class AdapterHolder(val tasarim: SepetRecyclerItemBinding) :
        RecyclerView.ViewHolder(tasarim.root)


}
