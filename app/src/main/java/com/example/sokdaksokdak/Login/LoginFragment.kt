package com.example.sokdaksokdak.Login

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sokdaksokdak.MainActivity
import com.example.sokdaksokdak.PolaSplashActivity
import com.example.sokdaksokdak.R
import com.example.sokdaksokdak.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider


class LoginFragment : Fragment() {
    private lateinit var loginViewModel : LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val GOOGLE_REQUEST_CODE = 99
    val TAG = "googleLogin"
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.kakaoLoginButton.setOnClickListener{
            loginViewModel.Login("kakao")
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = context?.let { GoogleSignIn.getClient(it,gso) }!!
        binding.googleLoginButton.setOnClickListener {
//            loginViewModel.Login("google")
            googleLogin()
        }

        return binding.root
    }

    fun googleLogin() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = context?.let { GoogleSignIn.getClient(it,gso) }!!
        googleSignIn()
    }

    // 구글 회원가입
    private fun googleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_REQUEST_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
                Log.i("google login",account.givenName + "구글 로그인 성공")
            } catch (e: ApiException) {
                //Toast.makeText(this, "구글 회원가입에 실패하였습니다: ${e.message}", Toast.LENGTH_SHORT).show()
                Log.i("google login","구글 로그인 실패")
            }
        } else {
            /*no-op*/
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "signInWithCredential:success")
                val user = auth.currentUser
//                toMainActivity(auth.currentUser)
            }
        }
    }


    private fun toMainActivity(user: FirebaseUser?) {
        if (user != null) {
            startActivity(Intent(context, PolaSplashActivity::class.java))
        }
    }

//    public override fun onStart() {
//        super.onStart()
//        //유저가 로그인되어있는지 확인
//        val currentUser = auth.currentUser
//        moveMain(currentUser)
//    }

    //유저가 로그인하면 메인액티비티로 이동
    private fun moveMain(user: FirebaseUser?) {
        if (user != null) {
            startActivity(Intent(context, MainActivity::class.java))
        }
    }


}