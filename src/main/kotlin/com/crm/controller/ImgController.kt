package com.crm.controller

import com.crm.exception.ErrorMsg
import com.crm.exception.HttpErrorStatus
import com.crm.exception.HttpStatusException
import com.crm.service.ImgService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileInputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import sun.plugin2.os.windows.Windows.ReadFile




@RestController
@RequestMapping("/imgs")
open class ImgController {

    @Value("\${imgSavePath}")
    lateinit var imgPath: String

    @Autowired
    lateinit var imgService: ImgService

    @GetMapping("/{imgName:.+}")
    open fun getImg(@PathVariable imgName: String, response: HttpServletResponse) {
        lateinit var imgData:ByteArray
        lateinit var imgName:String
        try {
            imgData = imgService.getImageData(imgName)
            imgName = imgName.substring(imgName.lastIndexOf('.')+1)
        }catch (e:Exception){
            imgData = this::class.java.getResource("/imgs/default.png").readBytes()
            imgName = "png"
        }
        response.contentType = "image/$imgName"
        response.outputStream.apply {
            write(imgData)
            flush()
            close()
        }
    }

    @PostMapping
    open fun uploadImg(@RequestParam("data") file: MultipartFile) {
        val fileName = file.originalFilename
        file.transferTo(File("$imgPath$fileName"))
    }
}