package com.example.mizu.features.authscreen.presentation.signup

import com.example.mizu.features.authscreen.presentation.login.AuthManager
import com.example.mizu.features.authscreen.utils.AuthInterface
import com.example.mizu.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.StateFlow

class SignUpRepository(
    private val authManager: AuthManager,
    private val dispatchersIO: CoroutineDispatcher
) : AuthInterface {

    val authSignUp: StateFlow<Result<String>> get() = authManager.authSignUpResult

    private val authScope = CoroutineScope(dispatchersIO + SupervisorJob())

    override suspend fun authSignUpWithEmailAndPassword(email: String, password: String) {
        authManager.authSignUp(email, password)
    }
}