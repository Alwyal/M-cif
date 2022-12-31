package com.farhanrv.catatankeuangan.ui.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.farhanrv.catatankeuangan.core.domain.usecase.RepositoryUseCase

class FinancialReportViewModel(private val repository: RepositoryUseCase) : ViewModel() {
    val notes = repository.getAllNote().asLiveData()
}