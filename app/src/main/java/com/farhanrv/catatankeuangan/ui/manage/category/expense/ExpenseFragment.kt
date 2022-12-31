package com.farhanrv.catatankeuangan.ui.manage.category.expense

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.farhanrv.catatankeuangan.R
import com.farhanrv.catatankeuangan.databinding.FragmentExpenseBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExpenseFragment : Fragment(R.layout.fragment_expense) {

    private val binding: FragmentExpenseBinding by viewBinding()
    private val viewModel: ExpenseViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val expenseAdapter = ExpenseAdapter()
        viewModel.category.observe(viewLifecycleOwner) { category ->
            val kategoriPengeluaran = category.filter { it.jenis == "Pengeluaran" }
            expenseAdapter.setCategoryList(kategoriPengeluaran)
        }

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        binding.recyclerView.adapter = expenseAdapter
    }
}