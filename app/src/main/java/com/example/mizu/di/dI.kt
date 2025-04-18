package com.example.mizu.di

import android.content.Context
import android.content.SharedPreferences
import com.example.mizu.features.authscreen.utils.authManager
import com.example.mizu.features.authscreen.login.source.LoginRepository
import com.example.mizu.features.authscreen.login.loginViewModel
import com.example.mizu.features.authscreen.signup.source.SignUpRepository
import com.example.mizu.features.authscreen.signup.signUpViewModel
import com.example.mizu.features.authscreen.common.authRepositoryCommon
import com.example.mizu.features.authscreen.common.authViewModelCommon
import com.example.mizu.features.calendarscreen.CalendarViewModel
import com.example.mizu.features.homescreen.HomeViewModel
import com.example.mizu.features.onboarding.viewModel.OnboardingViewModel
import com.example.mizu.features.onboarding.source.OnboardingRepository
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
    single<authManager>{
        authManager(get(named("ioDispatcher")),get())
    }

    single<authRepositoryCommon>{
        authRepositoryCommon(get(),get(named("ioDispatcher")))
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
    viewModel{ authViewModelCommon(get(named("ioDispatcher")), get()) }
    viewModel { loginViewModel(get(named("ioDispatcher")),get(),get()) }
    viewModel { signUpViewModel(get(named("ioDispatcher")),get(),get()) }


}