package com.example.data.repository

import com.example.domain.contract.dataSource.OnlineDataSource
import com.example.domain.contract.repository.SignUpRepository
import com.example.domain.model.signIn.SignInResponse
import com.example.domain.model.UserData
import com.example.domain.model.UserDataResponse
import com.example.domain.model.categories.CategoryItem
import javax.inject.Inject

class SignUpRepoImpl @Inject constructor(private val onlineDataSource: OnlineDataSource):SignUpRepository {

    override suspend fun signUp(userData: UserData): UserDataResponse {

        return onlineDataSource.signUp(userData)

    }

    override suspend fun signIn(userData: UserData): SignInResponse {
       return onlineDataSource.signIn(userData)
    }

    override suspend fun getAllCategories(): List<CategoryItem?>? {
        return onlineDataSource.getAllCategories()
    }
}