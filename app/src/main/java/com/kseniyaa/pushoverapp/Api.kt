package com.kseniyaa.pushoverapp

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    @POST("1/messages.json")
    fun pushMsg (@Body msg: Msg?): Call<Msg>

    @POST("1/users/login.json")
    fun getSecret(@Body logopass: Logopass?): Call<Resp>

    @POST("1/devices.json")
    fun getDeviceId(@Body device: Device?): Call<Resp>

    @GET("1/messages.json")
   fun getMsg(@Query("secret") secret: String?, @Query("device_id") device_id: String?): Call<Resp>

}