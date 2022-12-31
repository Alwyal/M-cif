package com.farhanrv.catatankeuangan.ui.manage.category.income

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.farhanrv.catatankeuangan.R
import com.farhanrv.catatankeuangan.databinding.FragmentExpenseBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class IncomeFragment : Fragment(R.layout.fragment_income) {

    private val binding: FragmentExpenseBinding by viewBinding()
    private val viewModel: IncomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val expenseAdapter = IncomeAdapter()
        viewModel.category.observe(viewLifecycleOwner) { category ->
            val kategoriPemasukan = category.filter { it.jenis == "Pemasukan" }
            expenseAdapter.setCategoryList(kategoriPemasukan)
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