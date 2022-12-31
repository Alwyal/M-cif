package com.farhanrv.catatankeuangan.di

import com.farhanrv.catatankeuangan.core.domain.usecase.RepositoryInteractor
import com.farhanrv.catatankeuangan.core.domain.usecase.RepositoryUseCase
import com.farhanrv.catatankeuangan.ui.SplashScreenViewModel
import com.farhanrv.catatankeuangan.ui.home.HomeViewModel
import com.farhanrv.catatankeuangan.ui.manage.category.add.AddCategoryViewModel
import com.farhanrv.catatankeuangan.ui.manage.category.expense.ExpenseViewModel
import com.farhanrv.catatankeuangan.ui.manage.category.income.IncomeViewModel
import com.farhanrv.catatankeuangan.ui.manage.note.add.AddNoteViewModel
import com.farhanrv.catatankeuangan.ui.manage.note.edit.EditNoteViewModel
import com.farhanrv.catatankeuangan.ui.report.FinancialReportViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<RepositoryUseCase> { RepositoryInteractor(get()) }
}

val viewModelModule = module {
    viewModel { SplashScreenViewModel(get()) }
    viewModel { AddNoteViewModel(get()) }
    viewModel { EditNoteViewModel(get()) }
    viewModel { AddCategoryViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { ExpenseViewModel(get()) }
    viewModel { IncomeViewModel(get()) }
    viewModel { FinancialReportViewModel(get()) }
}