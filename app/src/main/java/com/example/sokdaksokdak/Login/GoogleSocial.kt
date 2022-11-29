package com.example.sokdaksokdak.Login

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.sokdaksokdak.PolaSplashActivity
import com.example.sokdaksokdak.R
import com.example.sokdaksokdak.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GoogleSocial : AppCompatActivity(), SocialLogin {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var context : Context
//    private  val launcher : ActivityResultLauncher<Intent>


    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val GOOGLE_REQUEST_CODE = 99
    val TAG = "googleLogin"
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.inflate(layoutInflater)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("72536997949-mgi6or994u3rn0bfnseto9ucpnd2bdft.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(context,gso)

        setContentView(binding.root)
    }

    override fun Login(context: Context?) {
        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result ->
            if (result.resultCode == RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    firebaseAuthWithGoogle(account)
                    Log.i("google login",account.givenName + "구글 로그인 성공")
                } catch (e: ApiException) {
                    //Toast.makeText(this, "구글 회원가입에 실패하였습니다: ${e.message}", Toast.LENGTH_SHORT).show()
                    Log.i("google login","구글 로그인 실패")
                }
            }
        }
        if(context != null){
            this.context = context
            CoroutineScope(Dispatchers.IO).launch {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken("72536997949-mgi6or994u3rn0bfnseto9ucpnd2bdft.apps.googleusercontent.com")
                    .requestEmail()
                    .build()
                googleSignInClient = GoogleSignIn.getClient(context,gso)
                val signInIntent: Intent = googleSignInClient.signInIntent
                launcher.launch(signInIntent)
            }
        }

    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "signInWithCredential:success")
                val user = auth.currentUser
                toMainActivity(user)
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
//        toMainActivity(currentUser)
//    }


}