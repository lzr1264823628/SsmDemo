package com.crm.service

import com.crm.domain.mappers.UserMapper
import com.crm.domain.pojo.User
import com.crm.exception.HttpErrorStatus
import com.crm.exception.HttpStatusException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class UserService {

    @Autowired
    val userMapper:UserMapper?=null
        get() {
            field?:throw NullPointerException()
            return field
        }

    open fun addUser(u: User):User{
        try {
            if (!userMapper!!.add(u)) {
                throw HttpStatusException(HttpErrorStatus.UNPROCESABLE_ENTITY)
            }
        } catch (e: Exception) {
            throw HttpStatusException(HttpErrorStatus.UNPROCESABLE_ENTITY)
        }
        return userMapper!!.find(u)[0]
    }
}