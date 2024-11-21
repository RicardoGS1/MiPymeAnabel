package com.virtualworld.mipymeanabel

import android.app.Application
import com.virtualworld.mipymeanabel.id.initKoin
import io.ktor.http.ContentType
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class MiPymeAnabelApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MiPymeAnabelApp)
        }
    }

}