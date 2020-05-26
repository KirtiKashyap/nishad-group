package com.user.restapi.user.models

import javax.persistence.*
import javax.validation.constraints.NotBlank
@Entity(name="users")
data class User(@Id @GeneratedValue(strategy = GenerationType.AUTO) var id : Long = 0,
                @Column(unique = true)@get: NotBlank var email : String = "",
                @get: NotBlank var name : String = "",
                @get: NotBlank var password : String="",
                @get: NotBlank var phone : String=""){

}