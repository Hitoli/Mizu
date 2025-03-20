package com.example.mizu.di


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
