package com.crm.service

import com.crm.domain.mappers.StudentMapper
import com.crm.domain.pojo.Student
import com.crm.exception.HttpErrorStatus
import com.crm.exception.HttpStatusException
import com.github.pagehelper.PageHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
open class StudentService {
    @Autowired
    val _studentMapper: StudentMapper? = null
    private val studentMapper: StudentMapper
        get() {
            return _studentMapper ?: throw NullPointerException("studentMapper 创建失败")
        }

    @Value("\${pageNum}")
    var pageNum: Int = 1

    @Value("\${pageSize}")
    var pageSize: Int = 20

    open fun getAll(pageNum: Int?, pageSize: Int?): List<Student> {
        PageHelper.startPage(pageNum ?: this.pageNum, pageSize ?: this.pageSize)
        return studentMapper.findAll()
    }

    open fun getOne(sId:Int): Student? {
        return studentMapper.findById(sId)?:throw HttpStatusException(HttpErrorStatus.NOT_FOUND,"对象不存在")
    }

    open fun createStudent(s:Student):Student{
        try {
            if (!studentMapper.add(s)) {
                throw HttpStatusException(HttpErrorStatus.UNPROCESABLE_ENTITY)
            }
        } catch (e: Exception) {
            throw HttpStatusException(HttpErrorStatus.UNPROCESABLE_ENTITY)
        }
        s.sId = null
        return studentMapper.find(s)[0]
    }
    open fun updateStudent(sId:Int,s:Student): Student {
        var res = studentMapper.findById(sId) ?: throw HttpStatusException(HttpErrorStatus.NOT_FOUND, "请求对象不存在")
        try {
            s.sId = sId
            studentMapper.update(s).let {
                if(!it) throw HttpStatusException(HttpErrorStatus.INVALID_REQUEST,"对象属性错误")
            }
        }catch (e:Exception){
            throw HttpStatusException(HttpErrorStatus.UNPROCESABLE_ENTITY,"对象属性错误")
        }
        return studentMapper.findById(sId)!!
    }
    open fun deleteStudent(sId:Int){
        var res = studentMapper.findById(sId) ?: throw HttpStatusException(HttpErrorStatus.NOT_FOUND, "请求对象不存在")
        try {
            studentMapper.deleteById(sId)
        }catch (e:Exception){
            throw HttpStatusException(HttpErrorStatus.SERVER_ERROR)
        }
    }
}