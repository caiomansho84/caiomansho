package com.example.caiomansho.di

import android.content.Context
import com.example.caiomansho.data.repository.UserRepository
import com.example.caiomansho.data.repository.PersonRepository
import com.example.caiomansho.data.repository.WalletRepository
import com.example.caiomansho.domain.GetBalanceUseCase
import com.example.caiomansho.domain.GetBalanceUseCaseImpl
import com.example.caiomansho.domain.GetLoggedUseCase
import com.example.caiomansho.domain.GetLoggedUseCaseImpl
import com.example.caiomansho.domain.GetPersonUseCase
import com.example.caiomansho.domain.GetPersonUseCaseImpl
import com.example.caiomansho.domain.GetPersonsUseCase
import com.example.caiomansho.domain.GetPersonsUseCaseImpl
import com.example.caiomansho.domain.IsLoggedUseCase
import com.example.caiomansho.domain.IsLoggedUseCaseImpl
import com.example.caiomansho.domain.LoginUseCase
import com.example.caiomansho.domain.LoginUseCaseImpl
import com.example.caiomansho.domain.SearchPersonsUseCase
import com.example.caiomansho.domain.SearchPersonsUseCaseImpl
import com.example.caiomansho.domain.TransferUseCase
import com.example.caiomansho.domain.TransferUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext


@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideLoginUseCase(loginRepository: UserRepository): LoginUseCase {
        return LoginUseCaseImpl(loginRepository = loginRepository)
    }

    @Provides
    fun provideIsLoggedUseCase(loginRepository: UserRepository): IsLoggedUseCase {
        return IsLoggedUseCaseImpl(loginRepository = loginRepository)
    }

    @Provides
    fun provideGetPersonsUseCase(personRepository: PersonRepository): GetPersonsUseCase {
        return GetPersonsUseCaseImpl(personRepository = personRepository)
    }

    @Provides
    fun provideGetPersonUseCase(personRepository: PersonRepository): GetPersonUseCase {
        return GetPersonUseCaseImpl(personRepository = personRepository)
    }

    @Provides
    fun provideSearchPersonsUseCase(personRepository: PersonRepository): SearchPersonsUseCase {
        return SearchPersonsUseCaseImpl(personRepository = personRepository)
    }
    @Provides
    fun provideGetLoggedUseCase(userRepository: UserRepository): GetLoggedUseCase {
        return GetLoggedUseCaseImpl(userRepository = userRepository)
    }

    @Provides
    fun provideGetBalanceUseCase(walletRepository: WalletRepository): GetBalanceUseCase {
        return GetBalanceUseCaseImpl(walletRepository = walletRepository)
    }

    @Provides
    fun provideTransferUseCase(walletRepository: WalletRepository, @ApplicationContext context: Context): TransferUseCase {
        return TransferUseCaseImpl(context, walletRepository = walletRepository)
    }

}