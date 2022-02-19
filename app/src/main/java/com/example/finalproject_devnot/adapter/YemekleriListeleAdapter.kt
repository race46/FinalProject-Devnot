package com.example.finalproject_devnot.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject_devnot.R
import com.example.finalproject_devnot.databinding.AdapterYemekBinding
import com.example.finalproject_devnot.entity.Yemekler
import com.example.finalproject_devnot.fragment.yemekleri_listeleDirections
import com.example.finalproject_devnot.viewmodel.YemekleriListeleViewModel


class YemekleriListeleAdapter(private val yemekler: List<Yemekler>,val viewModel: YemekleriListeleViewModel) :
    RecyclerView.Adapter<YemekleriListeleAdapter.AdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AdapterHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.adapter_yemek, parent, false
            )
        )

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {
        holder.tasarim.yemek = yemekler[position]
        holder.tasarim.root.setOnClickListener{
            val action = yemekleri_listeleDirections.actionYemekleriListeleToYemekDetay(yemekler[position])
            Navigation.findNavController(holder.tasarim.root).navigate(action)
        }
        holder.tasarim.button.setOnClickListener{
            viewModel.yemekEkle(yemekler[position],1)
        }

    }

    override fun getItemCount() = yemekler.size
    class AdapterHolder(val tasarim: AdapterYemekBinding) :
        RecyclerView.ViewHolder(tasarim.root)
}
