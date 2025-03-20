package com.example.mizu.features.authscreen.presentation.login

import com.example.mizu.features.authscreen.utils.AuthInterface
import com.example.mizu.features.authscreen.utils.AuthManager
import com.example.mizu.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.StateFlow

class LoginRepository(
    private val authManager: AuthManager,
    private val dispatchersIO: CoroutineDispatcher
) : AuthInterface {
    val authSignIn: StateFlow<Result<String>> get() = authManager.authSignInResult

    private val authScope = CoroutineScope(dispatchersIO + SupervisorJob())
    override suspend fun authSignInWithEmailAndPassword(email: String, password: String) {
        authManager.authSignIn(email, password)
    }
}