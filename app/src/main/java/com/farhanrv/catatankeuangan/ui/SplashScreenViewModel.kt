package com.farhanrv.catatankeuangan.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.farhanrv.catatankeuangan.core.data.source.local.entity.CategoryEntity
import com.farhanrv.catatankeuangan.core.domain.usecase.RepositoryUseCase

class SplashScreenViewModel(private val repository: RepositoryUseCase) : ViewModel() {
    val category = repository.getCategory().asLiveData()
    fun insertCategory(data: List<CategoryEntity>) = repository.insertCategory(data)
}