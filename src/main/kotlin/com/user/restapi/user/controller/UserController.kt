package com.user.restapi.user.controller

import com.example.kotlinDemo.kotlinjournalapp.models.UserLogin

import com.user.restapi.user.models.User
import com.user.restapi.user.repository.UserRepository

import org.hibernate.exception.ConstraintViolationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import kotlin.collections.HashMap


@RestController
@RequestMapping("/api" )
class UserController(@Autowired private val userRepository :UserRepository){

    //get All users
    @GetMapping("/alluser")
    fun getAllUsers() : List<User> = userRepository.findAll()

    //creates a user
    @PostMapping("/signup",produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun createUser(@Valid user : User) :  ResponseEntity<HashMap<String, Any> > {
        val userMap = HashMap<String, Any>()
        try {
            userMap["user"]=userRepository.save(user)
            userMap["message"]="Success"
            userMap["isSuccess"]=true
        }catch (constraintException : ConstraintViolationException){
            userMap["message"]="Email is already exist"
            userMap["isSuccess"]=false
            return ResponseEntity.badRequest().body(userMap)
        }catch (e : Exception){
            userMap["message"]=e.toString()
            userMap["isSuccess"]=false
            return ResponseEntity.badRequest().body(userMap)
        }
        return ResponseEntity.accepted().body(userMap)
    }
    // get user
    @PostMapping("/login", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun getUser(@Valid userLogin: UserLogin)
            : ResponseEntity<HashMap<String, Any> >{

        val userMap = HashMap<String, Any>()
        if(userLogin.email==""){
            userMap["message"]="Enter Email"
            userMap["isSuccess"]=false
            return ResponseEntity.badRequest().body(userMap)
        }else if (userLogin.password==""){
            userMap["message"]="Enter Password"
            userMap["isSuccess"]=false
            return ResponseEntity.badRequest().body(userMap)
        }else{
            try {
                userMap["user"]=userRepository.findByEmailPassword(userLogin.email, userLogin.password)
                userMap["message"]="Success"
                userMap["isSuccess"]=true
            }catch (emptyResult : EmptyResultDataAccessException){
                userMap["message"]="You are not register yet.First register your self"
                userMap["isSuccess"]=false
                return ResponseEntity.badRequest().body(userMap)
            }catch (e : Exception){
                userMap["message"]=e.toString()
                userMap["isSuccess"]=false
                return ResponseEntity.badRequest().body(userMap)
            }
        }
        return ResponseEntity.accepted().body(userMap)
    }

}