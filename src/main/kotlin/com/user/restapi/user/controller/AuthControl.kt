package com.user.restapi.user.controller

import com.user.restapi.user.models.AuthenticationRequest
import com.user.restapi.user.models.AuthenticationResponse
import com.user.restapi.user.models.NewUser
import com.user.restapi.user.models.User
import com.user.restapi.user.service.MyUserDetail
import com.user.restapi.user.service.MyUserDetailService
import com.user.restapi.user.util.TokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestController
class AuthControl (@Autowired val authenticationManager: AuthenticationManager, @Autowired val myUserDetailService : MyUserDetailService, @Autowired val jwtToken : TokenUtil){
    @GetMapping("/hello")
    fun hello() : String{
        return "Hello Authentication"
    }

    @PostMapping("/authenticate")
    fun testAuthenticateToken(@RequestBody authenticationRequest: AuthenticationRequest) : ResponseEntity<*> {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(authenticationRequest.username, authenticationRequest.password))
        } catch (badceds: BadCredentialsException) {
            badceds.toString()
        } catch (emptyResultException : EmptyResultDataAccessException){
            emptyResultException.toString()
        }catch (exception: Exception) {
            exception.toString()
        }
        val userDetails: MyUserDetail = myUserDetailService.loadUserByUsername(authenticationRequest.username) as MyUserDetail

        var token: String = jwtToken.generateToken(userDetails)
        return ResponseEntity.ok(AuthenticationResponse(userDetails.user,token))
    }

    @RequestMapping(value = ["/register"], method = [RequestMethod.POST])
    @Throws(Exception::class)
    fun saveUser(@RequestBody newUser: NewUser): ResponseEntity<*> {
        var user=User()
        try{
             user =myUserDetailService.save(newUser)
             authenticationManager.authenticate(UsernamePasswordAuthenticationToken(user.email, user.password))
        }catch (badceds: BadCredentialsException) {
                badceds.toString()
        }catch (exception : Exception){
            exception.toString()
        }
        val userDetails: MyUserDetail = myUserDetailService.loadUserByUsername(user.email)as MyUserDetail
        var token: String = jwtToken.generateToken(userDetails)
        return ResponseEntity.ok(AuthenticationResponse(userDetails.user,token))
    }

}