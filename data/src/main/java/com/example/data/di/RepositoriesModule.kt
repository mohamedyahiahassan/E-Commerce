package com.example.data.di

import com.example.data.dataSource.OnlineDataSourceImpl
import com.example.data.repository.CartRepositoryImpl
import com.example.data.repository.ProductsRepositoryImpl
import com.example.data.repository.SignUpRepoImpl
import com.example.data.repository.SubCategoriesRepoImpl
import com.example.data.repository.WishlistRepoImpl
import com.example.domain.contract.dataSource.OnlineDataSource
import com.example.domain.contract.repository.CartRepository
import com.example.domain.contract.repository.ProductsRepository
import com.example.domain.contract.repository.SignUpRepository
import com.example.domain.contract.repository.SubCategoriesRepository
import com.example.domain.contract.repository.WishlistRepository
import com.example.domain.model.products.ProductItem
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    fun provideCategoryRepository(impl: SignUpRepoImpl): SignUpRepository{
        return impl
    }

    @Provides
    fun provideAllSubcategoriesOnCategoryRepository(impl: SubCategoriesRepoImpl): SubCategoriesRepository{
        return impl
    }

    @Provides
    fun provideOnlineDataSource(impl: OnlineDataSourceImpl):OnlineDataSource {
        return impl
    }

    @Provides
    fun provideProductsRepository(impl:ProductsRepositoryImpl): ProductsRepository{

        return impl
    }

    @Provides
    fun provideWishlist(impl:WishlistRepoImpl): WishlistRepository{

        return impl
    }

    @Provides
    fun provideCart(impl:CartRepositoryImpl): CartRepository{

        return impl
    }


}