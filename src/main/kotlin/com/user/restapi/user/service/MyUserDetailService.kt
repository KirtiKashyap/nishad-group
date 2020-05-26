package com.user.restapi.user.service

import com.user.restapi.user.models.NewUser
import com.user.restapi.user.models.User
import com.user.restapi.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class MyUserDetailService(@Autowired private val userRepository: UserRepository) :UserDetailsService {

override fun loadUserByUsername(username: String): UserDetails {
    var user : User = userRepository.findByUserName(username)
    return MyUserDetail(user)
}

    fun save(newUser : NewUser): User {
        var user =User()
        user.name = newUser.name
        user.password=newUser.password
        user.email=newUser.email
        user.phone=newUser.phone
        user.id=newUser.id
        return userRepository.save(user)

    }
}