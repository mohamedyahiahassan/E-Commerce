package com.example.data.model

import com.example.domain.model.signIn.SignInResponse
import com.example.domain.model.signIn.User

data class SignInResponseDTO(

    val statusMsg: String? = null,

    val message: String? = null,

    val user: UserDTO? = null,

    val token: String? = null
){

    fun toSignInResponse(): SignInResponse {

        return SignInResponse(statusMsg, message,user?.toUser(),token)
    }
}

data class UserDTO(

    val role: String? = null,

    val name: String? = null,

    val email: String? = null
){
    fun toUser(): User {

        return User(role, name, email)
    }

}
