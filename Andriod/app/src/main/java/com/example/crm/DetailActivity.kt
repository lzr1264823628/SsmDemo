package com.example.crm

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.crm.pojo.Student
import com.example.crm.services.AppService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail.*
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class DetailActivity : AppCompatActivity() {
    val service = AppService.getService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val id = intent.getIntExtra("sId", -1)
        if (id != -1) {
            service.getStudent(id).enqueue(object : Callback<Student> {
                    override fun onResponse(call: Call<Student>, response: Response<Student>) {
                        if (response.code() >= 400) {
                            val msg =
                                JSONArray(response.errorBody()).getJSONObject(0).getString("error")
                            Toast.makeText(this@DetailActivity.baseContext, msg, Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            val res = response.body()
                            editTextStuNum.setText(res?.stuNum)
                            editTextStuName.setText(res?.name)
                            editTextClass.setText(res?.`class`)
                            editTextDepartment.setText(res?.department)
                            editTextDorm.setText(res?.dorm)
                            editTextImageUrl.setText(res?.imageUrl)
                            editTextPhone.setText(res?.telephone)
                            if (res?.gender == true) {
                                radioGroupGender.check(R.id.radioBtnMale)
                            } else
                                radioGroupGender.check(R.id.radioBtnFemale)
                            val temp =  object : Observer<Bitmap> {
                                override fun onSubscribe(d: Disposable?) {

                                }

                                override fun onNext(t: Bitmap?) {
                                    imageProgressBar.visibility = View.INVISIBLE
                                    stuImageView.setImageBitmap(t)
                                }

                                override fun onError(e: Throwable?) {
                                    Toast.makeText(this@DetailActivity.baseContext, "网络请求错误", Toast.LENGTH_SHORT)
                                        .show()
                                }

                                override fun onComplete() {

                                }
                            }
                            service.imgData("imgs/" + if (res?.imageUrl != null && res.imageUrl?.isNotEmpty() == true) res.imageUrl else "default.png")
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .map {
                                    val bytes = it.bytes()
                                    BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                                }
                                .subscribe(temp)
                        }
                    }

                    override fun onFailure(call: Call<Student>, t: Throwable) {
                        Toast.makeText(
                            this@DetailActivity.baseContext,
                            "网络请求失败",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
    }
}