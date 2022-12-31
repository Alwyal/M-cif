package com.farhanrv.catatankeuangan.ui.manage.category.add

import com.farhanrv.catatankeuangan.core.domain.model.Category

interface AddCategoryCallback {
    fun onIconClicked(category: Category)
}