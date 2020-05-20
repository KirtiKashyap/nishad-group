package com.user.restapi.user.util

import com.user.restapi.user.service.MyUserDetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
@Component
class JwtRequestFilter (@Autowired val myUserDetailService: MyUserDetailService,@Autowired val jwtTokenUtil: TokenUtil): OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authorizationHeader : String = request.getHeader("Authorization")
        var username : String?=null
        var jwt : String?=null
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7)
            username = jwtTokenUtil.getUsernameFromToken(jwt!!)
        }
        if(username !=null && SecurityContextHolder.getContext().authentication==null){
            var userDetails : UserDetails=this.myUserDetailService.loadUserByUsername(username)
            if(jwtTokenUtil.validateToken(jwt, userDetails)){
                val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                usernamePasswordAuthenticationToken.details=WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication=usernamePasswordAuthenticationToken
            }
        }
        chain.doFilter(request,response)
    }



}