package com.example.mizu.features.authscreen.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.example.mizu.BuildConfig
import com.example.mizu.utils.Result
import com.example.mizu.utils.Utils
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import androidx.core.content.edit

class authManager(private val dispatchersIO: CoroutineDispatcher, private val sharedPreferences: SharedPreferences) {

    companion object {
        const val TAG = "Auth_Manager:"
    }

    private val _authSignUpResult: MutableStateFlow<Result<String>> = MutableStateFlow(Result.Loading)
    val authSignUpResult: StateFlow<Result<String>> get() = _authSignUpResult.asStateFlow()

    private val _authSignInResult: MutableStateFlow<Result<String>> = MutableStateFlow(Result.Loading)
    val authSignInResult: StateFlow<Result<String>> get() = _authSignInResult.asStateFlow()

    private val _authUserSignedIn:MutableStateFlow<Result<Boolean>> = MutableStateFlow(Result.Loading)
    val authUserSignedIn:StateFlow<Result<Boolean>> get() = _authUserSignedIn.asStateFlow()

    private val _authGoogleSignIn:MutableStateFlow<Result<Boolean>> = MutableStateFlow(Result.Loading)
    val authGoogleSignIn:StateFlow<Result<Boolean>> get() = _authGoogleSignIn.asStateFlow()

    private val _authGoogleSignOut:MutableStateFlow<Result<Boolean>> = MutableStateFlow(Result.Loading)
    val authGoogleSignOut:StateFlow<Result<Boolean>> get()= _authGoogleSignOut.asStateFlow()

    private val authScope = CoroutineScope(dispatchersIO + SupervisorJob())
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var activityContext: Context? = null
    private var credentialManager: CredentialManager? = null

    fun updateContext(context: Context) {
        activityContext = context
        credentialManager = CredentialManager.create(context)
        Utils.logIt(TAG, "ACTIVITY CONTEXT $activityContext")
    }

    suspend fun authSignUp(email: String, password: String) {
        authScope.launch {
            _authSignUpResult.emit(Result.Loading)
            try {
                val result = authSignUpWithEmailAndPassword(email, password)
                _authSignUpResult.emit(result)
            } catch (e: Exception) {
                _authSignUpResult.emit(Result.Failure(e.message.toString()))
            }
        }
    }

    private suspend fun authSignUpWithEmailAndPassword(
        email: String,
        password: String
    ): Result<String> {
        return suspendCoroutine { continuation ->
            try {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener { authResult ->
                        Utils.logIt(TAG, "Success authSignUpWithEmailAndPassword")
                        continuation.resume(Result.Success(authResult.user?.uid ?: "No UID"))
                    }
                    .addOnFailureListener { exception ->
                        Utils.logIt(TAG, "Failed authSignUpWithEmailAndPassword ${exception}")
                        continuation.resumeWithException(exception)
                    }
            }catch (e: Exception){
                Utils.logIt(TAG, "Failed authSignUpWithEmailAndPassword ${e.message.toString()}")
                continuation.resumeWithException(exception = e)
            }

        }
    }



    suspend fun authSignIn(email: String, password: String) {
        authScope.launch {
            _authSignInResult.emit(Result.Loading)
            try {
                val result = authSignInWithEmailAndPassword(email, password)
                _authSignInResult.emit(result)
            } catch (e: Exception) {
                _authSignInResult.emit(Result.Failure(e.message.toString()))
            }
        }
    }

    private suspend fun authSignInWithEmailAndPassword(
        email: String,
        password: String
    ): Result<String> = suspendCoroutine { continuation ->
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { authResult ->
                    Utils.logIt(TAG, "Success authSignInWithEmailAndPassword")
                    sharedPreferences.edit() { putString("UID", authResult.user?.uid) }
                    sharedPreferences.edit { putBoolean("isUserLoggedIn", true) }
                    continuation.resume(Result.Success("Success"))
                }
                .addOnFailureListener { exception ->
                    sharedPreferences.edit { putBoolean("isUserLoggedIn", false) }
                    Utils.logIt(TAG, "Failed authSignInWithEmailAndPassword: $exception")
                    continuation.resumeWithException(exception)
                }
        } catch (e: Exception) {
            Utils.logIt(TAG, "Failed authSignInWithEmailAndPassword ${e.message.toString()}")
            continuation.resumeWithException(exception = e)
        }
    }

    suspend fun googleSignIn(){
        authScope.launch {
            _authGoogleSignIn.emit(Result.Loading)
            Utils.logIt("googleSignIn", "Clicked googleSignIn")
            try {
                val result = authGoogleSignIn()
                Utils.logIt("googleSignIn", "Clicked result")

                _authGoogleSignIn.emit(Result.Success(result))
            }catch (e:Exception){
                e.printStackTrace()
                Utils.logIt("googleSignIn", "Clicked Exception")

                Utils.logIt(TAG, "Failed Auth Google Sign In${e.message}")
                _authGoogleSignIn.emit(Result.Failure(false))
            }
        }
    }
    private suspend fun authGoogleSignIn(): Boolean {
        if (authGoogleIsSignedIn()) {
            return true
        }
        Utils.logIt("authGoogleSignIn", "Clicked authGoogleSignIn")

        try {
            val credentialResponse = getCredentialResponse()
            Utils.logIt("authGoogleSignIn", "Result getCredentialResponse ${credentialResponse}")
            return if (credentialResponse!=null){
                handleSignInWithGoogle(credentialResponse)
            }else{
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Utils.logIt("authGoogleSignIn", "Exception getCredentialResponse ${e.message}")

            if (e is CancellationException) throw e
            Utils.logIt(TAG, "Failed authGoogleSignIn ${e.message}")
            return false
        }
    }

    private suspend fun handleSignInWithGoogle(response:GetCredentialResponse):Boolean{
        val credential = response.credential
        if (credential is  CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL){
            try {
                val tokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                Utils.logIt(TAG, "Google Sign In Name: ${tokenCredential.displayName}")
                Utils.logIt(TAG, "Google Sign In Email: ${tokenCredential.id}")
                Utils.logIt(TAG, "Google Sign In image: ${tokenCredential.profilePictureUri}")
                sharedPreferences.edit() { putString("user_name", tokenCredential.displayName) }
                val authCredential = GoogleAuthProvider.getCredential(tokenCredential.idToken,null)
                val authResult = auth.signInWithCredential(authCredential).await()
                return authResult.user!=null
            }catch (e:Exception){
                e.printStackTrace()
                Utils.logIt(TAG, "googleIdCredentialError ${e.message}")
                return false
            }

        }else{
            Utils.logIt(TAG, "Not the Google Credential handleSignInWithGoogle")
            return false
        }
    }

    private suspend fun getCredentialResponse(): GetCredentialResponse? {
            val request = GetCredentialRequest.Builder().addCredentialOption(
                GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false).setAutoSelectEnabled(false)
                    .setServerClientId(BuildConfig.SERVER_CLIENT_ID).build()
            ).build()
            // Show all the accounts instead of only Authorized once -> .setFilterByAuthorizedAccounts(false)
            // Auto Selecting the Account is -> .setAutoSelectEnabled(false)

          return credentialManager?.getCredential(request = request, context = activityContext!!)
    }

    suspend fun googleSignOut(){
        _authGoogleSignOut.emit(Result.Loading)
        try {
            val result = authGoogleSignOut()
            _authGoogleSignOut.emit(Result.Success(result))
        }catch (e:Exception){
            e.printStackTrace()
            Utils.logIt(TAG, "Google Sign Out Error${e.message}")
            _authGoogleSignOut.emit(Result.Failure(false))
        }
    }
    private suspend fun authGoogleSignOut():Boolean {
        credentialManager?.clearCredentialState(request = ClearCredentialStateRequest())
        sharedPreferences.edit { putBoolean("isUserLoggedIn", false) }
        restoreState()
        auth.signOut()
        return true
    }

    suspend fun isUserSignedIn(){
        _authUserSignedIn.emit(Result.Loading)
        try {
            val result = authGoogleIsSignedIn()
            Utils.logIt(TAG, "Current User ${result}")
            val isLoggedIn =  sharedPreferences.getBoolean("isUserLoggedIn",false)
            if (result || isLoggedIn){
                _authUserSignedIn.emit(Result.Success(true))
            }else{
                _authUserSignedIn.emit(Result.Failure(false))
            }
        }catch (e:Exception){
            e.printStackTrace()
            Utils.logIt(TAG,"User Signed In Error: ${e.message}")
            _authUserSignedIn.emit(Result.Failure(false))
        }
    }

    private suspend fun restoreState(){
        _authSignInResult.emit(Result.Loading)
        _authSignUpResult.emit(Result.Loading)
        _authUserSignedIn.emit(Result.Loading)
        _authGoogleSignIn.emit(Result.Loading)
        _authGoogleSignOut.emit(Result.Loading)
    }
    private fun authGoogleIsSignedIn(): Boolean {
        Utils.logIt(TAG, "Current User ${auth.currentUser!=null}")
        return auth.currentUser!=null
    }


}