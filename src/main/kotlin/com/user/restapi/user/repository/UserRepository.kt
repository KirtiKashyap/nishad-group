package com.user.restapi.user.repository

import com.user.restapi.user.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User,String>{

    @Query("FROM users WHERE email = :email and password = :password")
    fun findByEmailPassword(@Param("email") email: String, @Param("password") password: String): User


}