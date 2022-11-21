package com.example.sokdaksokdak.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getAll(): List<User>

    @Insert
    fun insert(user: User)

    @Query("Select * From user_table")
    fun getLogin() : User

    @Delete
    fun delete(user: User)

    @Query("INSERT INTO user_table(user_name, birth, social) VALUES (:user_name, :birth, :social)")
    fun insertUserData(user_name: String, birth: String, social: String)


//    @Query("select user_name from user_table")
//    fun getName():String

    @Query("select social from user_table")
    fun getSocial():String

    @Query("select * from user_table where userId like :id")
    fun getUserToId(id:Int):User

}