package com.example.sokdaksokdak.Login

import android.app.Application
import android.util.Log
import com.example.sokdaksokdak.database.User
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient


class RegisterUser(application: Application) {

    private val repository = LoginReposityory(application)


    public fun insertUser(name:String,userbitrh:String){//db에 유저정보 넣기
        repository.insert(User(name, userbitrh))
    }


//    public fun check(name:String,userbitrh:String){ //db에 유저정보가 있는지 확인 -> 있으면 true
//        if(repository.getname().toString()!=name)
//            insertUser(name,userbitrh)
//    }
}
