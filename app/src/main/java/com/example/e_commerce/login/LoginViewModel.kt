package com.example.e_commerce.login

import android.annotation.SuppressLint
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Token
import com.example.domain.contract.repository.SignUpRepository
import com.example.domain.model.signIn.SignInResponse
import com.example.domain.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: SignUpRepository):ViewModel() {

    val nameState = mutableStateOf<String>("")

    val nameErrorState = mutableStateOf<String?>(null)

    val emailState = mutableStateOf<String>("")

    val emailErrorState = mutableStateOf<String?>(null)

    val phoneState = mutableStateOf<String>("")

    val phoneErrorState = mutableStateOf<String?>(null)

    val passwordState = mutableStateOf<String>("")

    val passwordErrorState = mutableStateOf<String?>(null)

    val SignInErrorState = mutableStateOf<String?>(null)

    var response = mutableStateOf<SignInResponse?>(null)

    val isSuccessful = MutableLiveData<Boolean>(null)

    val isLoading = mutableStateOf<Boolean?>(null)


    fun signUpValidation(): Boolean {

        nameValidation()
        emailValidation()
        phoneValidation()
        passwordValidation()

        if (nameErrorState.value == null && emailErrorState.value == null && phoneErrorState.value == null && passwordErrorState.value == null) {

            return true
        } else {
            return false
        }


    }

    fun nameValidation() {
        if (nameState.value.isNullOrEmpty() || nameState.value.first() == ' ' || nameState.value.length < 4) {

            nameErrorState.value = "Invalid Name"

        } else {

            nameErrorState.value = null
        }
    }

    fun emailValidation() {

        if (!Patterns.EMAIL_ADDRESS.matcher(emailState.value).matches()) {

            emailErrorState.value = "Enter a valid email"
        } else {

            emailErrorState.value = null
        }
    }

    fun phoneValidation() {

        if (!Patterns.PHONE.matcher(phoneState.value)
                .matches() || phoneState.value.length != 11 || phoneState.value.first() != '0' || phoneState.value.contains(
                ' '
            )
        ) {

            phoneErrorState.value = "Enter a valid phone number"

        } else {

            phoneErrorState.value = null
        }
    }

    fun passwordValidation() {

        val uppercase: Pattern = Pattern.compile("[A-Z]")
        val lowercase: Pattern = Pattern.compile("[a-z]")

        if (passwordState.value.length < 8) {

            passwordErrorState.value = "Password should be at least 8 characters"

            return

        } else {

            passwordErrorState.value = null
        }

        if (!uppercase.matcher(passwordState.value).find()) {

            passwordErrorState.value = "Password should contain at least 1 Upper Case letter"
            return

        } else {

            passwordErrorState.value = null
        }

        if (!lowercase.matcher(passwordState.value).find()) {

            passwordErrorState.value = "Password should contain at least 1 Lower Case letter"
            return

        } else {

            passwordErrorState.value = null
        }


    }


    fun signUp() {

        if (signUpValidation() == false) {

            return
        } else {

            val userData =
                UserData(
                    password = passwordState.value,
                    phone = phoneState.value,
                    rePassword = passwordState.value,
                    name = nameState.value,
                    email = emailState.value,
                )


            viewModelScope.launch {

                val response = repository.signUp(userData)


            }
        }

    }


    @SuppressLint("SuspiciousIndentation")
    fun signIn() {

        if (!emailState.value.isNullOrBlank() && !passwordState.value.isNullOrBlank()) {





            viewModelScope.launch {

                 val temp = repository.signIn(
                    UserData(
                        email = emailState.value.trim(),
                        password = passwordState.value.trim()
                    )
                )

                response.value = temp


                    if (response.value!!.message.equals("success")){

                        Token.token = response.value!!.token

                        isSuccessful.value = true
                    } else {
                        isSuccessful.value = false
                        SignInErrorState.value = response.value!!.message.toString()
                    }


                isLoading.value = false

            }


        }
    }
}


