package com.user.restapi.user.service

import com.user.restapi.user.models.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MyUserDetail(user: User) : UserDetails {
    var user=user

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
       return ArrayList()
    }

    override fun isEnabled(): Boolean {
       return true
    }

    override fun getUsername(): String {
        return user.email
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
    fun getName() : String{
        return user.name
    }
    fun getPhone(): String{
        return user.phone
    }
    fun getId() : Long{
        return user.id
    }
}