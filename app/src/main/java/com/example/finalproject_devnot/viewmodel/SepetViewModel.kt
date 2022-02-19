package com.example.finalproject_devnot.viewmodel

import androidx.lifecycle.ViewModel
import com.example.finalproject_devnot.entity.Yemekler
import com.example.finalproject_devnot.repo.YemeklerDAORepository

class SepetViewModel :ViewModel() {
    private val krepo = YemeklerDAORepository()

    init {
        krepo.sepetGetir()
    }

    fun yemekSil(yemekAdi:String,postion:Int){
        krepo.yemekSil(yemekAdi, postion)
    }
    fun sepet() = krepo.sepet
    fun clear(){krepo.disposable.clear()}
}