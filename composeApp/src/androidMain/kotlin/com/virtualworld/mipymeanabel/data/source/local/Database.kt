package com.virtualworld.mipymeanabel.data.source.local

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

//actual class DatabaseFactory(private val app: Application) {
//    actual fun createRoomDatabase(): AppDatabase {
//        val dbFile = app.getDatabasePath("my_room.db")
//        return Room.databaseBuilder<AppDatabase>(
//            context = app,
//            name = dbFile.absolutePath,
//        )
//            .setDriver(BundledSQLiteDriver())
//            .setQueryCoroutineContext(Dispatchers.IO)
//            .build()
//    }
//
//
//}

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<AppDatabase> {
        val appContext = ctx.applicationContext
        val dbFile = appContext.getDatabasePath("my_room.db")
        return Room.databaseBuilder<AppDatabase>(
            context = appContext,
            name = dbFile.absolutePath
       )
    }