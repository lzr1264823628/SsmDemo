package com.crm.controller

import com.crm.domain.mappers.UserMapper
import com.crm.domain.pojo.User
import com.crm.exception.HttpErrorStatus
import com.crm.exception.HttpStatusException
import com.crm.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.session.Session
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/users")
open class UserController {
    @Autowired
    lateinit var userMapper: UserMapper
    @Autowired
    var userService:UserService?=null

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    open fun createUser(@RequestBody u: User):User{
        return userService!!.addUser(u)
    }

    @PostMapping("/login")
    open fun login(@RequestBody u:User,request:HttpServletRequest):Map<String,String>{
        val errorMsg = "用户名或密码错误"
        val user = userMapper.findByName(u.username?:throw HttpStatusException(HttpErrorStatus.INVALID_REQUEST,errorMsg))
        u.password = u.convertPassword()
        if(user.password==u.password){
            val session = request.getSession(true)
            session.setAttribute("uId",user.uId)
            return mapOf("X-Auth-Token" to session.id)
        }else{
            throw HttpStatusException(HttpErrorStatus.UNAUTHORIZED,errorMsg)
        }
    }

    @PostMapping("/logout")
    open fun logout(request:HttpServletRequest){
        val session = request.getSession(true)
        session.removeAttribute("uId")
        session.invalidate()
    }
}