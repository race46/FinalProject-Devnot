package com.example.finalproject_devnot.viewmodel

import androidx.lifecycle.ViewModel
import com.example.finalproject_devnot.entity.Yemekler
import com.example.finalproject_devnot.repo.YemeklerDAORepository

class YemekDetayViewModel :ViewModel() {
    private val krepo = YemeklerDAORepository()

    init {

    }

    fun yemekEkle(yemek:Yemekler,adet:Int){
        krepo.yemek_ekle(yemek,adet)
    }
    fun ekleYemekSuccess() = krepo.ekleYemekSuccess
    fun clear(){krepo.disposable.clear()}
}