package com.user.restapi.user.models

import javax.persistence.*
import javax.validation.constraints.NotBlank
@Entity(name="users")
data class User(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id : Long = 0,
                @Column(unique = true)@get: NotBlank val email : String = "",
                @get: NotBlank val name : String = "",
                @get: NotBlank val password : String="",
                @get: NotBlank val phone : String=""){
}