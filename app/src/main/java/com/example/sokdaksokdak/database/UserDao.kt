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

    @Query("SELECT birth FROM user_table WHERE user_name=:userName")
    fun getBirthByName(userName: String): Int

    @Query("select user_name from user_table")
    fun getnameList():List<String>

    @Query("select * from user_table where user_name=:name")
    fun getname(name:String):User
}