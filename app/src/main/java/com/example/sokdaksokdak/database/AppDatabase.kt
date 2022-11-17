package com.example.sokdaksokdak.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [User::class, Diary::class],
    version = 2,
    exportSchema = false
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun diaryDao() : DiaryDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "sokdak_database"
                    )
                        .build()
                }
            }
            return instance
        }
    }
}





