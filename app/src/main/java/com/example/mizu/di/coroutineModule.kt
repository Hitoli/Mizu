package com.example.mizu.di

import com.example.mizu.features.authscreen.presentation.login.AuthManager
import com.example.mizu.features.authscreen.presentation.login.LoginRepository
import com.example.mizu.features.authscreen.presentation.login.LoginViewModel
import com.example.mizu.features.authscreen.presentation.signup.SignUpRepository
import com.example.mizu.features.authscreen.presentation.signup.SignUpViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


//val CoroutineModule = module {
//    // Provide Main Dispatcher
//    single<CoroutineDispatcher>(named("mainDispatcher")) { Dispatchers.Main }
//    // Provide IO Dispatcher
//    single<CoroutineDispatcher>(named("ioDispatcher")) { Dispatchers.IO }
//    // Provide Default Dispatcher
//    single<CoroutineDispatcher>(named("defaultDispatcher")) { Dispatchers.Default }
//
//    single{
//        AuthManager(get(named("ioDispatcher")))
//    }
//    single<LoginRepository>{
//        LoginRepository(get(),get(named("ioDispatcher")))
//    }
//    single<SignUpRepository>{
//        SignUpRepository(get(),get(named("ioDispatcher")))
//    }
//
//    viewModel { LoginViewModel(get(),get(named("ioDispatcher"))) }
////    viewModel { SignUpViewModel(get(),get(named("ioDispatcher"))) }
//}
