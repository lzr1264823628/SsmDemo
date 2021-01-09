package com.crm.interceptor

import com.crm.exception.ErrorMsg
import com.crm.exception.HttpErrorStatus
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.session.Session
import org.springframework.session.jdbc.JdbcIndexedSessionRepository


fun HttpServletResponse.sendJsonAuthErrorMsg(any: Any){
    contentType = "application/json; charset=utf-8"
    setStatus(HttpErrorStatus.UNAUTHORIZED.code)
    val mapper = ObjectMapper()
    writer.print(mapper.writeValueAsString(ErrorMsg("需要登录验证")))
    writer.close()
    this.flushBuffer()
}

class LoginRequireInterceptor:HandlerInterceptor {
    @Autowired
    val jdbcIndexedSessionRepository:JdbcIndexedSessionRepository?=null

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
//        jdbcIndexedSessionRepository?.apply {
//            cleanUpExpiredSessions()
//        }
        if (request.method=="OPTIONS") return true
        val httpSession = request.getSession(false)
        if (httpSession==null){
            response.sendJsonAuthErrorMsg(ErrorMsg("需要登录验证"))
            return false
        }
        val uId:Int = (httpSession.getAttribute("uId")?:response.sendJsonAuthErrorMsg(ErrorMsg("需要登录验证")).also { return false }) as Int
        request.setAttribute("uId",uId)
        return true
    }
}