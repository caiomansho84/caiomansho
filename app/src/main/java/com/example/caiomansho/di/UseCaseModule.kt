package com.example.caiomansho.di

import com.example.caiomansho.data.repository.LoginRepository
import com.example.caiomansho.domain.IsLoggedUseCase
import com.example.caiomansho.domain.IsLoggedUseCaseImpl
import com.example.caiomansho.domain.LoginUseCase
import com.example.caiomansho.domain.LoginUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideLoginUseCase(loginRepository: LoginRepository): LoginUseCase {
        return LoginUseCaseImpl(loginRepository = loginRepository)
    }

    @Provides
    fun provideIsLoggedUseCase(loginRepository: LoginRepository): IsLoggedUseCase {
        return IsLoggedUseCaseImpl(loginRepository = loginRepository)
    }

}