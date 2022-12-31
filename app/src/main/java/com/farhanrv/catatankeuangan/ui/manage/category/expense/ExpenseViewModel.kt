package com.farhanrv.catatankeuangan.ui.manage.category.expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.farhanrv.catatankeuangan.core.domain.usecase.RepositoryUseCase

class ExpenseViewModel(private val repository: RepositoryUseCase) : ViewModel() {
    val category = repository.getCategory().asLiveData()
}