package com.cc.recipe4u.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cc.recipe4u.Objects.GlobalVariables
import com.cc.recipe4u.R
import com.cc.recipe4u.ViewModels.AuthViewModel
import com.cc.recipe4u.ViewModels.UserViewModel

class SplashActivity : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            checkUserStatus()
        }, 2500)
    }

    private fun checkUserStatus() {
        authViewModel.isUserSignedIn.observe(this) { isSignedIn ->
            if (isSignedIn) {
                val userid = authViewModel.currentUser.value!!.uid
                val userViewModel = UserViewModel(userid)

                userViewModel.userLiveData.observe(this) { userdata ->
                    GlobalVariables.currentUser = userdata
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}