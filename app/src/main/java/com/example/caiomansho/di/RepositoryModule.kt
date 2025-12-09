package com.example.caiomansho.di

import com.example.caiomansho.data.datasource.LoginDataSource
import com.example.caiomansho.data.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindLoginRepository(
        implementation: LoginDataSource
    ): LoginRepository

}