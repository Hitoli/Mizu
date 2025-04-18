package com.example.mizu.features.authscreen.login.source

import android.content.Context
import com.example.mizu.features.authscreen.utils.authInterface
import com.example.mizu.features.authscreen.utils.authManager
import com.example.mizu.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.StateFlow

class LoginRepository(
    private val authManager: authManager,
    private val dispatchersIO: CoroutineDispatcher
) : authInterface {
    val authSignIn: StateFlow<Result<String>> get() = authManager.authSignInResult

    private val authScope = CoroutineScope(dispatchersIO + SupervisorJob())
    override suspend fun authSignInWithEmailAndPassword(email: String, password: String) {
        authManager.authSignIn(email, password)
    }
    fun updateAuthManagerContext(context: Context){
        authManager.updateContext(context = context)
    }
}