package com.example.caiomansho.di

import com.example.caiomansho.data.repository.LoginRepository
import com.example.caiomansho.data.repository.PersonRepository
import com.example.caiomansho.domain.GetPersonsUseCase
import com.example.caiomansho.domain.GetPersonsUseCaseImpl
import com.example.caiomansho.domain.IsLoggedUseCase
import com.example.caiomansho.domain.IsLoggedUseCaseImpl
import com.example.caiomansho.domain.LoginUseCase
import com.example.caiomansho.domain.LoginUseCaseImpl
import com.example.caiomansho.domain.SearchPersonsUseCase
import com.example.caiomansho.domain.SearchPersonsUseCaseImpl
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

    @Provides
    fun provideGetPersons(personRepository: PersonRepository): GetPersonsUseCase {
        return GetPersonsUseCaseImpl(personRepository = personRepository)
    }

    @Provides
    fun provideSearchPersons(personRepository: PersonRepository): SearchPersonsUseCase {
        return SearchPersonsUseCaseImpl(personRepository = personRepository)
    }

}