package com.example.crm.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crm.DetailActivity
import com.example.crm.LoginActivity
import com.example.crm.R
import com.example.crm.pojo.Student
import com.example.crm.services.AppService
import retrofit2.Call
import retrofit2.Response



class HomeFragment : Fragment() {
    val appService: AppService = AppService.getService()
    private val listAdapter: ListAdapter = ListAdapter(mutableListOf())
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }

    override fun onResume() {
        super.onResume()
        val studentListView = requireView().findViewById<RecyclerView>(R.id.student_list)
        studentListView.layoutManager = LinearLayoutManager(this.context)
        studentListView.adapter = listAdapter
        listAdapter.getStudentList()
    }
    inner class ListAdapter(private var studentList: List<Student>) :
        RecyclerView.Adapter<ListAdapter.ViewHolder>() {
        fun getStudentList() {
            appService.getStudents().enqueue(object : retrofit2.Callback<List<Student>> {
                @SuppressLint("ShowToast")
                override fun onResponse(
                    call: Call<List<Student>>,
                    response: Response<List<Student>>
                ) {
                    if (response.code() >= 400) {
                        if (AppService.getToken() != null) Toast.makeText(
                            context,
                            "登录过期",
                            Toast.LENGTH_SHORT
                        ).show()
                        else Toast.makeText(context, "需要登录", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(context, LoginActivity::class.java))
                    } else {
                        val data = response.body() as List<Student>
                        this@ListAdapter.studentList = data
                        Toast.makeText(AppService.context, "数据获取成功", Toast.LENGTH_SHORT).show()
                        this@ListAdapter.notifyDataSetChanged()
                    }
                }

                @SuppressLint("ShowToast")
                override fun onFailure(call: Call<List<Student>>, t: Throwable) {
                    Toast.makeText(AppService.context, "网络连接异常", Toast.LENGTH_SHORT).show()
                }
            })
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val stuNum: TextView = view.findViewById(R.id.stuNum)
            val stuName: TextView = view.findViewById(R.id.stuName)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val a = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
            val viewHolder = ViewHolder(a)
            viewHolder.itemView.setOnClickListener {
                val position = viewHolder.adapterPosition
                val sid = studentList[position].sid
                val intend = Intent(this@HomeFragment.context, DetailActivity::class.java)
                intend.putExtra("sId",sid)
                this@HomeFragment.startActivity(intend)
            }

            return viewHolder
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.stuName.text = studentList[position].name
            holder.stuNum.text = studentList[position].stuNum
        }

        override fun getItemCount(): Int {
            return studentList.size
        }
    }
}

