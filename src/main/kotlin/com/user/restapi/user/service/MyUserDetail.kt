package com.user.restapi.user.service

import com.user.restapi.user.models.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MyUserDetail(user: User) : UserDetails {
    var userName : String=user.email
    var myName : String=user.name
    var myPassword: String=user.password
    var myPhone: String=user.phone
    var myId: Long=user.id

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
       return ArrayList()
    }

    override fun isEnabled(): Boolean {
       return true
    }

    override fun getUsername(): String {
        return userName
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return myPassword
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
    fun getName() : String{
        return myName
    }
    fun getPhone(): String{
        return myPhone
    }
    fun getId() : Long{
        return myId
    }
}