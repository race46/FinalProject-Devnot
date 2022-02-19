package com.example.finalproject_devnot.fragment

import android.app.AlertDialog
import android.content.Context
import android.inputmethodservice.Keyboard
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.finalproject_devnot.R
import com.example.finalproject_devnot.databinding.FragmentYemekDetayBinding
import com.example.finalproject_devnot.viewmodel.YemekDetayViewModel


class yemek_detay : Fragment() {
    private val args by navArgs<yemek_detayArgs>()
    private lateinit var tasarim: FragmentYemekDetayBinding
    private lateinit var viewModel: YemekDetayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val tempViewModel: YemekDetayViewModel by viewModels()
        viewModel = tempViewModel
        tasarim = FragmentYemekDetayBinding.inflate(layoutInflater)
        tasarim.yemek = args.yemek

        tasarim.yemekEkle.setOnClickListener {

            if(tasarim.adet.text.isBlank()) tasarim.adet.setText("0")
            val adet = tasarim.adet.text.toString().toInt()
            tasarim.adet.onEditorAction(EditorInfo.IME_ACTION_DONE);
            viewModel.yemekEkle(args.yemek, adet)
        }


        observe()
        return tasarim.root
    }

    fun observe() {
        viewModel.ekleYemekSuccess().observe(viewLifecycleOwner) {
            if (it == 1) {
                showAlertDialogButtonClicked(R.layout.success)
                viewModel.ekleYemekSuccess().value = 10
                findNavController().popBackStack()
            }else if (it == 0 || it == -1){
                showAlertDialogButtonClicked(R.layout.error)
            }
        }
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