package com.farhanrv.catatankeuangan.core.data.source.local

import com.farhanrv.catatankeuangan.assets.R
import com.farhanrv.catatankeuangan.core.data.source.local.entity.CategoryEntity
import com.farhanrv.catatankeuangan.core.domain.model.Category

object CategoryList {
    fun categoryIconList(): List<Category> {
        val category = ArrayList<Category>()

        category.addAll(
            listOf(
                Category(1, R.drawable.ic_category_android),
                Category(2, R.drawable.ic_category_antenna),
                Category(3, R.drawable.ic_category_assignment),
                Category(4, R.drawable.ic_category_cart),
                Category(5, R.drawable.ic_category_clothes),
                Category(6, R.drawable.ic_category_compass),
                Category(7, R.drawable.ic_category_date_calendar),
                Category(8, R.drawable.ic_category_delete),
                Category(9, R.drawable.ic_category_dollar),
                Category(10, R.drawable.ic_category_eat),
                Category(11, R.drawable.ic_category_electric),
                Category(12, R.drawable.ic_category_game),
                Category(13, R.drawable.ic_category_health),
                Category(14, R.drawable.ic_category_home),
                Category(15, R.drawable.ic_category_lamp),
                Category(16, R.drawable.ic_category_people_group),
                Category(17, R.drawable.ic_category_pet),
                Category(18, R.drawable.ic_category_question_chat),
                Category(19, R.drawable.ic_category_school),
                Category(20, R.drawable.ic_category_statistic),
                Category(21, R.drawable.ic_category_time_and_task),
                Category(22, R.drawable.ic_category_train),
                Category(23, R.drawable.ic_category_work)
            )
        )
        return category
    }
    fun categoryList(): List<CategoryEntity> {
        val category = ArrayList<CategoryEntity>()

        category.addAll(
            listOf(
                CategoryEntity(1, "Internet", "Pengeluaran", R.drawable.ic_category_antenna),
                CategoryEntity(2, "Pakaian", "Pengeluaran", R.drawable.ic_category_clothes),
                CategoryEntity(3, "Gaji", "Pemasukan", R.drawable.ic_category_dollar),
                CategoryEntity(4,"Makanan & Minuman", "Pengeluaran", R.drawable.ic_category_eat),
                CategoryEntity(5,"Listrik", "Pengeluaran", R.drawable.ic_category_electric),
                CategoryEntity(6, "Kesehatan", "Pengeluaran", R.drawable.ic_category_health),
                CategoryEntity(7, "Pengembalian Dana", "Pemasukan", R.drawable.ic_category_dollar),
                CategoryEntity(8, "Pakan Hewan", "Pengeluaran", R.drawable.ic_category_pet),
                CategoryEntity(9, "Transportasi", "Pengeluaran", R.drawable.ic_category_train),
                CategoryEntity(10, "Undian", "Pemasukan", R.drawable.ic_category_assignment),
                CategoryEntity(10, "Lainnya", "Pemasukan", R.drawable.ic_category_statistic),
            )
        )
        return category
    }
}