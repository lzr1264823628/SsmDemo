package com.crm.exception

enum class HttpErrorStatus(val code:Int){
    // POST/PUT/PATCH 请求参数格式错误
    INVALID_REQUEST(400),
    // 没有权限
    UNAUTHORIZED(401),
    // 有权限但不可以访问
    FORBIDDEN(403),
    // 请求对象不存在
    NOT_FOUND(404),
    // POST/PUT/PATCH 字段验证错误
    NOT_ACCEPTABLE(406),
    // 用户请求的资源已经被删除
    GONE(410),
    UNPROCESABLE_ENTITY(422),
    SERVER_ERROR(500)
}

class HttpStatusException(val errorStatus:HttpErrorStatus, var msg:String = "发生错误"
) : Exception()