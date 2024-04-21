package com.example.mizu.di

import com.example.mizu.features.homescreen.view_model.HomeViewModel
import com.example.mizu.features.onboarding.view_model.OnboardingViewModel
import com.example.mizu.model.OnboardingRepository
import com.example.mizu.presentation_app.navmap.NavScreen
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val DIModule = module {
    single<OnboardingRepository> {
        OnboardingRepository(get())
    }
    viewModel { OnboardingViewModel(get()) }
    viewModel{HomeViewModel()}



}