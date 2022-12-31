package com.farhanrv.catatankeuangan.core.di

import androidx.room.Room
import com.farhanrv.catatankeuangan.core.data.AppRepository
import com.farhanrv.catatankeuangan.core.data.source.local.AppExecutors
import com.farhanrv.catatankeuangan.core.data.source.local.LocalDataSource
import com.farhanrv.catatankeuangan.core.data.source.local.room.AppDatabase
import com.farhanrv.catatankeuangan.core.domain.repository.IRepository
import com.farhanrv.catatankeuangan.core.domain.usecase.RepositoryInteractor
import com.farhanrv.catatankeuangan.core.domain.usecase.RepositoryUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { get<AppDatabase>().appDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "catatan_keuangan.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    factory { AppExecutors() }
    single<IRepository> { AppRepository(get(), get()) }
}