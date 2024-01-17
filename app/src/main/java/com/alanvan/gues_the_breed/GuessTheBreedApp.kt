package com.alanvan.gues_the_breed

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.alanvan.gues_the_breed.di.appModule
import com.alanvan.gues_the_breed.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GuessTheBreedApp : Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@GuessTheBreedApp)
            modules(appModule, networkModule)
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this).build()
    }
}
