package com.crm.domain.pojo

import org.apache.commons.codec.binary.Hex
import java.security.MessageDigest




data class User(
    var uId:Int?=null,
    var username:String?=null,
    var password:String?=null,
    var email:String?=null
){
    fun convertPassword(): String? {
        val messageDigest = MessageDigest.getInstance("SHA-256")
        val hash: ByteArray = messageDigest.digest(password!!.toByteArray(charset("UTF-8")))
        return Hex.encodeHexString(hash)
    }
}
