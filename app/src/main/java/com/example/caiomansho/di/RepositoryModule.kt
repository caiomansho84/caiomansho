package com.example.caiomansho.di

import com.example.caiomansho.data.datasource.UserDataSource
import com.example.caiomansho.data.datasource.PersonDataSource
import com.example.caiomansho.data.datasource.WalletDataSource
import com.example.caiomansho.data.repository.UserRepository
import com.example.caiomansho.data.repository.PersonRepository
import com.example.caiomansho.data.repository.WalletRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindLoginRepository(
        implementation: UserDataSource
    ): UserRepository

    @Binds
    fun bindPersonRepository(
        implementation: PersonDataSource
    ): PersonRepository

    @Binds
    fun bindWalletRepository(
        implementation: WalletDataSource
    ): WalletRepository


}