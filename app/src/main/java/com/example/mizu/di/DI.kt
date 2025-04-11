package com.example.mizu.di

import android.content.Context
import android.content.SharedPreferences
import com.example.mizu.features.authscreen.utils.AuthManager
import com.example.mizu.features.authscreen.presentation.login.LoginRepository
import com.example.mizu.features.authscreen.presentation.login.LoginViewModel
import com.example.mizu.features.authscreen.presentation.signup.SignUpRepository
import com.example.mizu.features.authscreen.presentation.signup.SignUpViewModel
import com.example.mizu.features.authscreen.utils.AuthRepositoryCommon
import com.example.mizu.features.calendarscreen.view_model.CalendarViewModel
import com.example.mizu.features.homescreen.view_model.HomeViewModel
import com.example.mizu.features.onboarding.viewModel.OnboardingViewModel
import com.example.mizu.model.OnboardingRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val DIModule = module {
    // Provide Main Dispatcher
    single<CoroutineDispatcher>(named("mainDispatcher")) { Dispatchers.Main }
    // Provide IO Dispatcher
    single<CoroutineDispatcher>(named("ioDispatcher")) { Dispatchers.IO }
    // Provide Default Dispatcher
    single<CoroutineDispatcher>(named("defaultDispatcher")) { Dispatchers.Default }

    single<SharedPreferences>{
        androidContext().getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    single<OnboardingRepository> {
        OnboardingRepository(get())
    }
    single<AuthManager>{
        AuthManager(get(named("ioDispatcher")),get())
    }

    single<AuthRepositoryCommon>{
        AuthRepositoryCommon(get(),get(named("ioDispatcher")))
    }

    single<LoginRepository>{
        LoginRepository(get(),get(named("ioDispatcher")))
    }

    single<SignUpRepository>{
        SignUpRepository(get(),get(named("ioDispatcher")))
    }
    viewModel { OnboardingViewModel(get()) }
    viewModel { HomeViewModel(get(), androidApplication()) }
    viewModel { CalendarViewModel(get()) }
    viewModel { LoginViewModel(get(named("ioDispatcher")),get(),get()) }
    viewModel { SignUpViewModel(get(named("ioDispatcher")),get(),get()) }


}