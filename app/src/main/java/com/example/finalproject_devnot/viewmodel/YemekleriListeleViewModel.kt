package com.example.finalproject_devnot.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject_devnot.entity.Menu
import com.example.finalproject_devnot.entity.SepetYemekler
import com.example.finalproject_devnot.entity.Yemekler
import com.example.finalproject_devnot.repo.YemeklerDAORepository

class YemekleriListeleViewModel :ViewModel() {
    private var menu = MutableLiveData<Menu>()
    private val krepo = YemeklerDAORepository()

    init {
        krepo.menuAl()
        menu = krepo.menuGetir()
    }

    fun ekleYemekSucces():MutableLiveData<Int> = krepo.ekleYemekSuccess
    fun clear(){krepo.disposable.clear()}
    fun menu() = menu
    fun yemekEkle(yemek: Yemekler, adet : Int){
        krepo.yemek_ekle(yemek,adet)
    }

}