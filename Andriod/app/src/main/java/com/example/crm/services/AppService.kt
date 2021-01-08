package com.example.crm.services

import android.content.Context
import android.content.SharedPreferences
import com.example.crm.pojo.Student
import com.example.crm.pojo.Token
import com.example.crm.pojo.User
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*



const val baseUrl = "http://192.168.1.248:9000/student_crm/"
const val tokenName = "X-Auth-Token"
interface AppService {

    companion object{
        @JvmStatic
        lateinit var context: Context
        val base_url: String
            get() = baseUrl
        val token_name: String
            get() = tokenName

        fun getService():AppService{
            return Retrofit.Builder()
                .baseUrl(base_url)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(genericClient())
                .build().create(AppService::class.java)
        }
        private fun genericClient(): OkHttpClient {

            return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request()
                        .newBuilder()
                        .addHeader(
                            "Content-Type",
                            "application/json; charset=UTF-8"
                        )
                        .addHeader("Accept-Encoding", "gzip, deflate")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("Accept", "*/*").also {
                            getToken()?.apply { it.addHeader(token_name, this)  }
                        }
                        .build()
                    chain.proceed(request)
                }
                .build()
        }
        fun getToken():String?{
            val sp: SharedPreferences = context.getSharedPreferences(
                token_name,
                Context.MODE_PRIVATE
            )
            return sp.getString(token_name, null)
        }
        fun setToken(token: String){
            val sp: SharedPreferences = context.getSharedPreferences(
                token_name,
                Context.MODE_PRIVATE
            )
            sp.edit().apply {
                this.putString(token_name, token)
                this.apply()
            }
        }
    }

    @POST("users/login")
    fun login(@Body u: User): Call<Token>

    @POST("users/logout")
    fun logout():Call<Unit>

    @GET("students")
    fun getStudents(
        @Query("pageNum") pageNum: Int? = null,
        @Query("pageSize") pageSize: Int? = null
    ):Call<List<Student>>

    @GET("students/{sId}")
    fun getStudent(@Path("sId") sId: Int):Call<Student>

    @POST("students")
    fun createStudent(@Body s: Student):Call<Student>

    @PUT("students/{sId}")
    fun changeStudent(@Path("sId") sId: Int, @Body s: Student):Call<Student>

    @DELETE("/students/{sId}")
    fun deleteStudent(@Path("sId") sId: Int):Call<Any>

    @GET
    fun imgData(@Url fileUrl: String?): Observable<ResponseBody>

}

