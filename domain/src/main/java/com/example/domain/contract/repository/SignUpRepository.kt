package com.example.domain.contract.repository

import com.example.domain.model.signIn.SignInResponse
import com.example.domain.model.UserData
import com.example.domain.model.UserDataResponse
import com.example.domain.model.categories.CategoryItem

interface SignUpRepository {

    suspend fun signUp(userData: UserData): UserDataResponse

    suspend fun signIn(userData: UserData): SignInResponse




}