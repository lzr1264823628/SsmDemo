package com.crm.exception

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class HttpStatusResolver {
    @ExceptionHandler(HttpStatusException::class)
    @ResponseBody
    fun handle(e: HttpStatusException, response: HttpServletResponse): ErrorMsg {
        response.contentType = "application/json;charset=utf-8"
        response.setStatus(e.errorStatus.code)
        return ErrorMsg(e.msg)
    }
}