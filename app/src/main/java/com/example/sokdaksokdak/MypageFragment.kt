package com.example.sokdaksokdak

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.sokdaksokdak.Login.LoginViewModel
import com.example.sokdaksokdak.Login.RegisterUser
import com.example.sokdaksokdak.databinding.FragmentDiaryBinding
import com.example.sokdaksokdak.databinding.FragmentLoginBinding
import com.example.sokdaksokdak.databinding.FragmentMypageBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.kakao.sdk.user.UserApiClient

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MypageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MypageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var loginViewModel : LoginViewModel
    private lateinit var binding: FragmentMypageBinding
    var auth : FirebaseAuth?= null
    var googleSignInClient : GoogleSignInClient?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMypageBinding.inflate(inflater, container, false)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                //google
                var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build()
                googleSignInClient = context?.let { GoogleSignIn.getClient(it, gso) }

                // firebaseauth를 사용하기 위한 인스턴스 get
                auth = FirebaseAuth.getInstance()

                binding.mypageName.text = FirebaseAuth.getInstance().currentUser?.displayName
                // 로그아웃
                binding.logoutBtn.setOnClickListener {
                    FirebaseAuth.getInstance().signOut()
                    googleSignInClient?.signOut()
                    Log.d("구글","로그아웃 성공")

                    var logoutIntent = Intent (context, PolaNaviActivity::class.java)
                    logoutIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(logoutIntent)

                }
                //탈퇴
                binding.secessionBtn.setOnClickListener{
                    FirebaseAuth.getInstance().signOut()
                    googleSignInClient?.revokeAccess()
                    auth!!.currentUser?.delete()
                    Log.d("구글","탈퇴 성공")

                    var logoutIntent = Intent (context, PolaNaviActivity::class.java)
                    logoutIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(logoutIntent)
                }
            } else if(tokenInfo != null) {
                //kakao
                UserApiClient.instance.me { user, error ->
                    if (user != null) {
                        var name = user.kakaoAccount?.profile?.nickname.toString()
                        var birth = user.kakaoAccount?.birthday.toString()
                        Log.d("이름","이름 : "+name)
                        binding.mypageName.text = name
                        binding.mypageBirth.text = birth
                    }
                }
                // 로그아웃
                binding.logoutBtn.setOnClickListener {
                    UserApiClient.instance.logout { error ->
                        if (error != null) {
                            Log.d("카카오", " 로그아웃 실패")
                        }else {
                            Log.d("카카오", "로그아웃 성공")
                        }
                        val intent = Intent(context, PolaNaviActivity::class.java)
                        startActivity(intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP))
                    }
                }
                //탈퇴
                binding.secessionBtn.setOnClickListener{
                    UserApiClient.instance.unlink { error ->
                        if (error != null) {
                            Log.d("카카오", "탈퇴 실패")
                        }else {
                            Log.d("카카오", " 탈퇴 성공")
                            //db 데이터 삭제
                            val intent = Intent(context, PolaNaviActivity::class.java)
                            startActivity(intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP))
                        }
                    }
                }

            }
        }
        return binding.root
    }

}