package com.farhanrv.catatankeuangan.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.farhanrv.catatankeuangan.core.domain.model.Note
import com.farhanrv.catatankeuangan.core.domain.usecase.RepositoryUseCase
import com.farhanrv.catatankeuangan.core.utils.RupiahHelper
import kotlin.math.abs

class HomeViewModel(private val repository: RepositoryUseCase) : ViewModel() {

    val notes = repository.getAllNote().asLiveData()

    fun getBalanceIncomeExpense(data: List<Note>): List<String> {
        val income =
            data.filter { it.jenis == "Pemasukan" }.sumOf { it.nominal.toInt() }
        val expense =
            data.filter { it.jenis == "Pengeluaran" }.sumOf { it.nominal.toInt() }
        val balance = RupiahHelper.rupiahFormatter(income+expense)
        val rupiahIncome = RupiahHelper.rupiahFormatter(income)
        val rupiahExpense = RupiahHelper.rupiahFormatter(abs(expense))
        val balanceIncomeExpense = ArrayList<String>()
        balanceIncomeExpense.addAll(
            listOf(
                balance,
                rupiahIncome,
                rupiahExpense
            )
        )
        return balanceIncomeExpense
    }

    fun getDateList(data: List<Note>): List<Long> {
        val response = ArrayList<Long>()
        val distinct = data.distinctBy { it.date }
        for (item in distinct) {
            response.add(item.date)
        }
        return response
    }
}