package com.user.restapi.user.controller

import com.user.restapi.userfilestorage.FileStorage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api" )
class UploadImageController {
    @Autowired
    lateinit var fileStorage: FileStorage

//    @GetMapping("/")
//    fun index(): String {
//        return "multipartfile/uploadform.html"
//    }

    @PostMapping("/upload")
    fun uploadMultipartFile(@RequestParam("uploadfile") file: MultipartFile, model: Model): ResponseEntity<HashMap<String, Any>> {
        val fileMap = HashMap<String, Any>()
        try {
            fileStorage.store(file);
            model.addAttribute("message", "File uploaded successfully! -> filename = " + file.getOriginalFilename())
            fileMap["message"] = "File uploaded Successfully"
            fileMap["isSuccess"] = true
        }catch (e: Exception){
            fileMap["message"] = e.toString()
            fileMap["isSuccess"] = false
        }
        return ResponseEntity.accepted().body(fileMap)
    }
}