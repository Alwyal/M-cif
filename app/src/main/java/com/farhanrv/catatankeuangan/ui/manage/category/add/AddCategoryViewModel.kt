package com.farhanrv.catatankeuangan.ui.manage.category.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.farhanrv.catatankeuangan.core.data.source.local.entity.CategoryEntity
import com.farhanrv.catatankeuangan.core.domain.usecase.RepositoryUseCase

class AddCategoryViewModel(private val repository: RepositoryUseCase) : ViewModel() {
    val category = repository.getCategory().asLiveData()
    fun insertCategory(category: CategoryEntity) {
        repository.insertCategory(listOf(category))
    }

    fun updateCategory(category: CategoryEntity) {
        repository.updateCategory(category)
    }
}