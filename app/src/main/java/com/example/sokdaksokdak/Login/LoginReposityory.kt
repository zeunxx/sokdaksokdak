package com.example.sokdaksokdak.Login

import android.app.Application
import com.example.sokdaksokdak.database.AppDatabase
import com.example.sokdaksokdak.database.User
import com.example.sokdaksokdak.database.UserDao

class LoginReposityory(application: Application) {
    private val appDatabase = AppDatabase.getInstance(application)!!
    private val userDao : UserDao = appDatabase.userDao()


    fun insert(user:User){
        try{
            val thread = Thread(Runnable {
                userDao.insert(user)
            })
            thread.start()
        }catch(e:Exception){e.printStackTrace()}
    }

    fun insertData(username:String, userbirth:String, social:String){
        try{
            val thread = Thread(Runnable {
                userDao.insertUserData(username,userbirth,social)
            })
            thread.start()
        }catch(e:Exception){e.printStackTrace()}
    }
    fun delete(user:User){
        try{
            val thread = Thread(Runnable {
                userDao.delete(user)
            })
            thread.start()
        }catch(e:Exception){e.printStackTrace()}
    }
    fun getSocial(){
        try{
            val thread = Thread(Runnable {
                userDao.getSocial()
            })
            thread.start()
        }catch(e:Exception){e.printStackTrace()}
    }

    fun getUserToId(userId:Int){
        try{
            val thread = Thread(Runnable {
                userDao.getUserToId(userId)
            })
            thread.start()
        }catch(e:Exception){e.printStackTrace()}
    }

}