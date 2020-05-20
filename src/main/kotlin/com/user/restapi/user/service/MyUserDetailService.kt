package com.user.restapi.user.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class MyUserDetailService :UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return User("kirti","password",ArrayList())
    }
}