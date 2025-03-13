package com.example.mizu.features.authscreen.presentation.login

import com.example.mizu.utils.Utils
import com.google.firebase.auth.FirebaseAuth

class AuthManager(var auth:FirebaseAuth = FirebaseAuth.getInstance()) {
    private fun authSignUpWithEmailAndPassword(email:String, password:String){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isComplete){
                Utils.logIt("Sign Up", "Success")
            }
        }
    }
}