package com.user.restapi.user.controller

import com.user.restapi.user.models.AuthenticationRequest
import com.user.restapi.user.models.AuthenticationResponse
import com.user.restapi.user.service.MyUserDetailService
import com.user.restapi.user.util.TokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthControl (@Autowired val authenticationManager: AuthenticationManager, @Autowired val myUserDetail : MyUserDetailService, @Autowired val jwtToken : TokenUtil){
    @GetMapping("/hello")
    fun hello() : String{
        return "Hello Authentication"
    }
    @PostMapping("/authenticate")
    fun createAuthenticatToken(@RequestBody authenticationRequest: AuthenticationRequest) : ResponseEntity<*>{
       try {
           authenticationManager.authenticate(UsernamePasswordAuthenticationToken(authenticationRequest.username, authenticationRequest.password))
       }catch (badceds : BadCredentialsException){
           badceds.toString()
       }catch (exception : Exception){
           exception.toString()
       }
        val userDetails : UserDetails= myUserDetail.loadUserByUsername(authenticationRequest.username)
        var token : String=jwtToken.generateToken(userDetails)
        return ResponseEntity.ok(AuthenticationResponse(token))
    }
}