package com.example.mizu.features.authscreen.signup.source

import com.example.mizu.features.authscreen.utils.authInterface
import com.example.mizu.features.authscreen.utils.authManager
import com.example.mizu.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.StateFlow

class SignUpRepository(
    private val authManager: authManager,
    private val dispatchersIO: CoroutineDispatcher
) : authInterface {

    val authSignUp: StateFlow<Result<String>> get() = authManager.authSignUpResult
    private val authScope = CoroutineScope(dispatchersIO + SupervisorJob())

    override suspend fun authSignUpWithEmailAndPassword(email: String, password: String) {
        authManager.authSignUp(email, password)
    }

}