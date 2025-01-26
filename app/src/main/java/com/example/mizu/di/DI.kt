package com.example.mizu.di

import com.example.mizu.features.calendarscreen.view_model.CalendarViewModel
import com.example.mizu.features.homescreen.view_model.HomeViewModel
import com.example.mizu.features.onboarding.viewModel.OnboardingViewModel
import com.example.mizu.model.OnboardingRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val DIModule = module {
    single<OnboardingRepository> {
        OnboardingRepository(get())
    }
    viewModel { OnboardingViewModel(get()) }
    viewModel{HomeViewModel(get(),androidApplication())}
    viewModel{ CalendarViewModel(get()) }




}