package com.farhanrv.catatankeuangan.core.di

import android.app.Application
import com.farhanrv.catatankeuangan.di.useCaseModule
import com.farhanrv.catatankeuangan.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class ApplicationDI : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@ApplicationDI)
            modules(
                listOf(
                    databaseModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}