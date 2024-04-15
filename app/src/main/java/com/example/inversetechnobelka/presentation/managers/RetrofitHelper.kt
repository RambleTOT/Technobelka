package com.example.inversetechnobelka.presentation.managers

import com.example.inversetechnobelka.data.util.ApiMethod
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object {

        private val BASE_URL = "https://technoshooter.postideas.store/api/"

    }

    fun getApi() : ApiMethod {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiMethod::class.java)
    }

}