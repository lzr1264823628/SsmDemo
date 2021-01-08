package com.crm.service

import com.crm.exception.HttpErrorStatus
import com.crm.exception.HttpStatusException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File

@Service
open class ImgService {
    @Value("\${imgSavePath}")
    lateinit var imgPath: String

    open fun getImageData(imgName: String): ByteArray {
        val file = File("$imgPath$imgName")
        if (!file.exists()) {
            throw HttpStatusException(HttpErrorStatus.NOT_FOUND, "图片不存在")
//            println("sds")
        }
        return file.readBytes()
    }

    open fun test(){
        throw HttpStatusException(HttpErrorStatus.NOT_FOUND, "图片不存在")

    }
}