package com.crm.controller

import com.crm.domain.mappers.StudentMapper
import com.crm.domain.pojo.Student
import com.crm.exception.HttpErrorStatus
import com.crm.exception.HttpStatusException
import com.crm.service.StudentService
import com.github.pagehelper.PageHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/students")
open class StudentController {
    @Autowired
    val _studentMapper: StudentMapper? = null

    @Autowired
    var studentService: StudentService?=null

    private val studentMapper: StudentMapper
        get() {
            return _studentMapper ?: throw NullPointerException("studentMapper 创建失败")
        }

    @GetMapping()
    open fun getStudentList(@RequestParam(required = false) pageNum:Int?, @RequestParam(required = false) pageSize:Int?): List<Student> {
        return studentService!!.getAll(pageNum,pageSize)
    }

    @GetMapping("/{sId}")
    open fun getStudent(@PathVariable(value = "sId") sId: Int): Student? {
        return studentService!!.getOne(sId)
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    open fun createStudent(@RequestBody s: Student): Student {
        return studentService!!.createStudent(s)
    }

    @PutMapping("/{sId}")
    @ResponseStatus(HttpStatus.CREATED)
    open fun changeStudent(@PathVariable sId: Int, @RequestBody s: Student): Student {
        return studentService!!.updateStudent(sId,s)
    }

    @DeleteMapping("/{sId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    open fun deleteStudent(@PathVariable sId: Int){
        studentService!!.deleteStudent(sId)
    }

}