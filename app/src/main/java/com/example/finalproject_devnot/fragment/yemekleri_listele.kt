package com.example.finalproject_devnot.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.finalproject_devnot.R
import com.example.finalproject_devnot.adapter.YemekleriListeleAdapter
import com.example.finalproject_devnot.databinding.FragmentYemekleriListeleBinding
import com.example.finalproject_devnot.viewmodel.YemekleriListeleViewModel

class yemekleri_listele : Fragment() {
    private lateinit var tasarim:FragmentYemekleriListeleBinding
    private lateinit var viewModel:YemekleriListeleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_yemekleri_listele, container, false)
        val tempViewModel:YemekleriListeleViewModel by viewModels()
        viewModel = tempViewModel
        tasarim.recyclerView.layoutManager = GridLayoutManager(context,1)

        viewModel.menu().observe(viewLifecycleOwner) {
            tasarim.recyclerView.adapter = YemekleriListeleAdapter(it.yemekler,viewModel)
            tasarim.progressBar.visibility = View.GONE
        }

        tasarim.basket.setOnClickListener{
            findNavController().navigate(R.id.action_yemekleri_listele_to_sepet)
        }
        viewModel.ekleYemekSucces().observe(viewLifecycleOwner){
            if(it == 1){
                viewModel.ekleYemekSucces().value = 10
                showAlertDialogButtonClicked(R.layout.success)
            }else if(it == 0 || it == -1){
                viewModel.ekleYemekSucces().value = 10
                showAlertDialogButtonClicked(R.layout.error)
            }
        }



        return tasarim.root
    }
    fun showAlertDialogButtonClicked(layout:Int) {
        val builder = AlertDialog.Builder(requireContext())
        val customLayout: View = layoutInflater
            .inflate(layout, null)
        builder.setView(customLayout)
        val dialog = builder.create()
        customLayout.findViewById<Button>(R.id.tamam).setOnClickListener{
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }
}