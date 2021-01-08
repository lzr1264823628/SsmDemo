package com.example.crm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crm.pojo.Token
import com.example.crm.pojo.User
import com.example.crm.services.AppService
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login_btn.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()
            val s = AppService.getService()
            s.login(User(username= username,password = password)).enqueue(
                object: Callback<Token> {
                    override fun onResponse(call: Call<Token>, response: Response<Token>) {
                        if(response.code() >=400){
                            val msg = JSONArray(response.errorBody()).getJSONObject(0).getString("error")
                            Toast.makeText(this@LoginActivity,msg,Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this@LoginActivity,"登录成功",Toast.LENGTH_SHORT).show()
                            response.body()?.`X-Auth-Token`?.let { it1 -> AppService.setToken(it1) }
                            this@LoginActivity.startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                        }
                    }

                    override fun onFailure(call: Call<Token>, t: Throwable) {
                        Toast.makeText(this@LoginActivity,"网络连接异常",Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }
}