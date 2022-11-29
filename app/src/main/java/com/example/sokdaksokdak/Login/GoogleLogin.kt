//package com.example.sokdaksokdak.Login
//
//import android.app.Application
//import android.content.Intent
//import android.util.Log
//import androidx.activity.result.ActivityResult
//import androidx.activity.result.ActivityResultLauncher
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.core.app.ActivityCompat.startActivityForResult
//import androidx.core.content.ContextCompat.startActivity
//import androidx.lifecycle.AndroidViewModel
//import com.example.sokdaksokdak.MainActivity
//import com.example.sokdaksokdak.PolaSplashActivity
//import com.example.sokdaksokdak.R
//import com.google.android.gms.auth.api.signin.GoogleSignIn
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount
//import com.google.android.gms.auth.api.signin.GoogleSignInClient
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions
//import com.google.android.gms.common.api.ApiException
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseUser
//import com.google.firebase.auth.GoogleAuthProvider
//
//class GoogleLogin(application: Application) : SocialLogin, AndroidViewModel(application) {
//    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
//    val GOOGLE_REQUEST_CODE = 99
//    val TAG = "googleLogin"
//    private lateinit var googleSignInClient: GoogleSignInClient
//    private val context = getApplication<Application>().applicationContext
//    private lateinit var getResultImage: ActivityResultLauncher<Intent>
//    var launcher: ActivityResultLauncher<String>? = null
//
//
//
//    override fun Login() {
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken("72536997949-mgi6or994u3rn0bfnseto9ucpnd2bdft.apps.googleusercontent.com")
//            .requestEmail()
//            .build()
//
//        googleSignInClient = context?.let { GoogleSignIn.getClient(it,gso) }!!
//        val signInIntent = googleSignInClient.signInIntent
//
//
//
//    }
//
//    fun googleLogin() {
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken("72536997949-mgi6or994u3rn0bfnseto9ucpnd2bdft.apps.googleusercontent.com")
//            .requestEmail()
//            .build()
//
//        googleSignInClient = context?.let { GoogleSignIn.getClient(it,gso) }!!
//        googleSignIn()
//    }
//
//    // 구글 회원가입
//    private fun googleSignIn() {
//        val signInIntent = googleSignInClient.signInIntent
//        startActivityForResult(signInIntent, GOOGLE_REQUEST_CODE)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == GOOGLE_REQUEST_CODE) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                val account = task.getResult(ApiException::class.java)
//                firebaseAuthWithGoogle(account)
//                Log.i("google login",account.givenName + "구글 로그인 성공")
//            } catch (e: ApiException) {
//                //Toast.makeText(this, "구글 회원가입에 실패하였습니다: ${e.message}", Toast.LENGTH_SHORT).show()
//                Log.i("google login","구글 로그인 실패")
//            }
//        } else {
//            /*no-op*/
//        }
//    }
//
//    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
//        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
//        auth.signInWithCredential(credential).addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                Log.d(TAG, "signInWithCredential:success")
//                val user = auth.currentUser
////                toMainActivity(auth.currentUser)
//            }
//        }
//    }
//
//
//    private fun toMainActivity(user: FirebaseUser?) {
//        if (user != null) {
//            startActivity(Intent(context, PolaSplashActivity::class.java))
//        }
//    }
//
//
//
//}