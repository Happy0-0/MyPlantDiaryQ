package edu.uc.jonesbr.myplantdiary

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitClientInstance {

    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://www.plantplaces.com"

    val retroFitInstance : Retrofit?
        get() {
            //has this object been created yet?
            if(retrofit == null) {
                //Create it!
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

}