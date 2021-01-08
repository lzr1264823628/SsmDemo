package com.example.crm

import android.util.Log
import android.widget.Toast
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.crm.pojo.Student
import com.example.crm.pojo.Token
import com.example.crm.pojo.User
import com.example.crm.services.AppService

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.security.auth.login.LoginException
import kotlin.math.log

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.crm", appContext.packageName)
    }

    @Test
    fun testService() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val s: AppService = AppService.also { it.context = appContext }.getService()
//        s.getStudents().enqueue(
//            object : Callback<List<Student>> {
//                override fun onResponse(
//                    call: Call<List<Student>>,
//                    response: Response<List<Student>>
//                ) {
//                    Log.i("service", "onResponse: $response")
//                    if (response.code() >= 400) {
//                        Log.i("service",response.errorBody()?.string())
//                    }
//                }
//
//                override fun onFailure(call: Call<List<Student>>, t: Throwable) {
//                    Log.i("service", "onFailure: ${t.toString()}")
//                }
//            })
        s.login(User(username = "admin",password = "qwe123")).enqueue(object : Callback<Token> {
            override fun onFailure(call: Call<Token>, t: Throwable) {
                Log.e("service", "onFailure: $t",)
            }

            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                Log.w("service", "onResponse: ${AppService.getToken()}", )
                Log.i("service", "onResponse: ${(response.body() as Token).`X-Auth-Token`}")
                val res = response.body() as Token
                res.`X-Auth-Token`?.let { AppService.setToken(it) }
                Toast.makeText(appContext,"asdasdasdsa",Toast.LENGTH_LONG)
            }
        })
//        s.logout().enqueue(
//            object : Callback<Unit> {
//                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
//                    Log.w("service", "onResponse: ${response.code()}", )
//                }
//
//                override fun onFailure(call: Call<Unit>, t: Throwable) {
//                    Log.e("service", "onFailure: $t",)
//                }
//            }
//        )
    }
}