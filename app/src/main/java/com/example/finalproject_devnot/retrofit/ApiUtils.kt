package com.example.finalproject_devnot.retrofit

class ApiUtils {
    companion object{
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getKisilerDaoInterface() : YemeklerDAOInterface {
            return RetrofitClient.getClient(BASE_URL).create(YemeklerDAOInterface::class.java)
        }
    }
}