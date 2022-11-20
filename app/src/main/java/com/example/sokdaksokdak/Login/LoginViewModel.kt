package com.example.sokdaksokdak.Login

import android.app.Application
import android.provider.ContactsContract.Profile
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.sokdaksokdak.database.User
import com.example.sokdaksokdak.writeDiary.WriteDiary
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import org.apache.commons.lang3.ObjectUtils.Null

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private var registerUser = RegisterUser(application)
    private lateinit var name : String
    private lateinit var birth : String

    fun Login(social : String) {
        if (social == "kakao")
            kakaoLogin();
        else if(social == "google")
            googleLogin()
    }

    private fun googleLogin() {
        TODO("Not yet implemented")
    }

    fun kakaoLogin(){
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e("실패", "카카오계정으로 로그인 실패", error)
            } else if (token != null) {
                Log.i("성공", "카카오계정으로 로그인 성공 ${token.accessToken}")
                getInfo()
                UserApiClient.instance.me { user, error ->
                    if (user != null) {
                        name = user.kakaoAccount?.profile?.nickname.toString()
                        birth = user.kakaoAccount?.birthday.toString()
                        registerUser.insertUser(name,birth)
                    }
                }

            }
        }
        // kakao login에는 카카오톡, 카카오계정 로그인 두가지
        // 카카오톡 설치되어 있으면 이를 우선으로
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    Log.e("실패", "카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                } else if (token != null) {
                    Log.i("성공", "카카오톡으로 로그인 성공 ${token.accessToken}")
               }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }

    }
    fun getInfo() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("정보 실패", "사용자 정보 요청 실패", error)
            } else if (user != null) {
                Log.i(
                    "정보 성공", "사용자 정보 요청 성공" +
                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                            "\n생일: ${user.kakaoAccount?.birthday}"
                )
            }
        }
    }

}

