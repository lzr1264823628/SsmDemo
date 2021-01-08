package com.example.crm.data

import android.widget.Toast
import com.example.crm.data.model.LoggedInUser
import com.example.crm.pojo.Token
import com.example.crm.pojo.User
import com.example.crm.services.AppService
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    val appService = AppService.getService()
    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            appService.login(User(username = username,password = password)).enqueue(
                object : retrofit2.Callback<Token>{
                    override fun onResponse(call: Call<Token>, response: Response<Token>) {
                        if (response.code()>=400){
                            if(response.errorBody()!=null)
                                throw Throwable(JSONArray(response.errorBody()).getJSONObject(0).getString("error"))
                        }else{

                        }
                    }

                    override fun onFailure(call: Call<Token>, t: Throwable) {
                        throw t
                    }
                }
            )
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}