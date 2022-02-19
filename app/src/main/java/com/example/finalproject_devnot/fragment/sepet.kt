package com.example.finalproject_devnot.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject_devnot.R
import com.example.finalproject_devnot.adapter.SepetAdapter
import com.example.finalproject_devnot.databinding.FragmentSepetBinding
import com.example.finalproject_devnot.databinding.FragmentYemekDetayBinding
import com.example.finalproject_devnot.viewmodel.SepetViewModel
import com.example.finalproject_devnot.viewmodel.YemekDetayViewModel

class sepet : Fragment() {
    private lateinit var tasarim: FragmentSepetBinding
    private lateinit var viewModel:SepetViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val tempViewModel: SepetViewModel by viewModels()
        viewModel = tempViewModel
        tasarim = FragmentSepetBinding.inflate(layoutInflater)

        tasarim.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        viewModel.sepet().observe(viewLifecycleOwner) {
            tasarim.recyclerview.adapter = SepetAdapter(it.sepet_yemekler,viewModel)
            var total = 0
            for (i in it.sepet_yemekler){
                total += i.yemek_fiyat.toInt()*i.yemek_siparis_adet.toInt()
            }
            tasarim.btnTamamla.setText("Siparişi tamamla  (₺${total})")
        }



        return tasarim.root
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }

}