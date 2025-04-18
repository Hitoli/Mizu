package com.example.mizu.features.authscreen.common

import com.example.mizu.features.authscreen.utils.authInterface
import com.example.mizu.features.authscreen.utils.authManager
import com.example.mizu.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.StateFlow

class authRepositoryCommon(
    private val authManager: authManager,
    private val dispatchersIO: CoroutineDispatcher
) : authInterface {

    val authGoogleSignIn: StateFlow<Result<Boolean>> get() = authManager.authGoogleSignIn
    val authGoogleSignOut: StateFlow<Result<Boolean>> get() = authManager.authGoogleSignOut
    val authIsUserSignedIn: StateFlow<Result<Boolean>> get() = authManager.authUserSignedIn
    private val authScope = CoroutineScope(dispatchersIO + SupervisorJob())

    override suspend fun authGoogleSignIn() {
        authManager.googleSignIn()
    }

    override suspend fun authSignOut() {
        authManager.googleSignOut()
    }

    override suspend fun authIsUserSignedIn() {
        authManager.isUserSignedIn()
    }


}