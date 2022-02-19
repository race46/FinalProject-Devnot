package com.example.finalproject_devnot.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.finalproject_devnot.entity.Menu
import com.example.finalproject_devnot.entity.Sepet
import com.example.finalproject_devnot.entity.SepetYemekler
import com.example.finalproject_devnot.entity.Yemekler
import com.example.finalproject_devnot.retrofit.ApiUtils
import com.example.finalproject_devnot.retrofit.YemeklerDAOInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class YemeklerDAORepository {
    var menu: MutableLiveData<Menu>
    var kdao: YemeklerDAOInterface
    var sepet: MutableLiveData<Sepet>
    var ekleYemekSuccess: MutableLiveData<Int>
    val disposable: CompositeDisposable
    val sepetInit = MutableLiveData<Sepet>()

    init {
        kdao = ApiUtils.getKisilerDaoInterface()
        menu = MutableLiveData()
        sepet = MutableLiveData()
        disposable = CompositeDisposable()
        ekleYemekSuccess = MutableLiveData(10)
        println("yemekler dao repo")
    }

    fun menuGetir(): MutableLiveData<Menu> {
        return menu
    }

    fun menuAl() {
        disposable.add(
            kdao.getYemekler()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ value -> menu.value = value },
                    { error -> println("Error: $error") },
                    { println("Completed") })
        )
    }

    fun yemek_ekle(yemek: Yemekler, adet: Int) {
        disposable.add(
            kdao.yemekEkle(
                yemek.yemek_adi,
                yemek.yemek_resim_adi,
                yemek.yemek_fiyat.toInt(),
                adet,
                "muhammed_yildiz"
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ value ->
                    Log.e("error", value.toString())
                    ekleYemekSuccess.value = value.success
                    if (value.success == 1) {
                        sepet.value!!.sepet_yemekler.add(
                            SepetYemekler(
                                "muhammed_yildiz",
                                "0",
                                yemek.yemek_adi,
                                yemek.yemek_fiyat,
                                yemek.yemek_resim_adi,
                                adet.toString()
                            )
                        )
                    }
                },
                    { error ->
                        Log.e("error", error.toString())
                        if (error.toString() != "java.lang.NullPointerException")
                            ekleYemekSuccess.value = -1
                    },
                    { println("Completed") })
        )
    }

    fun sepetGetir() {
        disposable.add(
            kdao.sepetGetir("muhammed_yildiz")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ value -> sepetDuzenle(value) },
                    { error ->
                        if (error.toString() == "java.io.EOFException: End of input at line 6 column 1 path \$") {
                            sepetDuzenle(
                                Sepet(arrayListOf(), 1)
                            )
                        }
                    },
                    { println("Completed") })
        )
    }

    fun sepetDuzenle(sepetx: Sepet) {
        sepetInit.value = sepetx
        val map = HashMap<String, SepetYemekler>()
        for (yemek in sepetx.sepet_yemekler) {
            if (map.containsKey(yemek.yemek_adi)) {
                val adet =
                    map.get(yemek.yemek_adi)!!.yemek_siparis_adet.toInt() + yemek.yemek_siparis_adet.toInt()
                map.get(yemek.yemek_adi)!!.yemek_siparis_adet = adet.toString()
            } else {
                map.put(yemek.yemek_adi, yemek)
            }
        }
        val sepet_fix = arrayListOf<SepetYemekler>()
        for (i in map.values) {
            sepet_fix.add(i)
        }
        sepet.value = Sepet(sepet_fix, 1)

    }

    fun yemekSil(yemekAdi: String, postion: Int) {
        for (i in sepetInit.value!!.sepet_yemekler) {
            if (i.yemek_adi == yemekAdi) {
                disposable.add(
                    kdao.yemek_sil(i.sepet_yemek_id.toInt(), i.kullanici_adi)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ value -> sepetGetir() },
                            { error -> },
                            { println("Completed") })
                )
            }
        }
    }

}
