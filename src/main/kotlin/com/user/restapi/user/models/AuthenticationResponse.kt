package com.user.restapi.user.models

data class AuthenticationResponse(var userDetail: User, var jwt: String)