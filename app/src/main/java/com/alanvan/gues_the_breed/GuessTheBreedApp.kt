package com.alanvan.gues_the_breed

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import org.koin.core.context.startKoin

class GuessTheBreedApp : Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidC
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this).build()
    }
}
