package com.farhanrv.catatankeuangan.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.farhanrv.catatankeuangan.R
import com.farhanrv.catatankeuangan.databinding.FragmentHomeBinding
import com.farhanrv.catatankeuangan.ui.manage.note.add.AddNoteActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var appBarCallback: AppBarCallback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val homeAdapter = HomeParentAdapter()
            viewModel.notes.observe(viewLifecycleOwner) { noteList ->
                if (noteList != null) {
                    val dateList = viewModel.getDateList(noteList)
                    homeAdapter.setListNotes(noteList, dateList)

                    val balanceIncomeExpense = viewModel.getBalanceIncomeExpense(noteList)
                    appBarCallback.changeSaldo(balanceIncomeExpense[0])
                    binding.tvPemasukan.text = "Pemasukan : ${balanceIncomeExpense[1]}"
                    binding.tvPengeluaran.text = "Pengeluaran : ${balanceIncomeExpense[2]}"
                }
            }

            binding.recyclerViewMain.setHasFixedSize(true)
            binding.recyclerViewMain.adapter = homeAdapter

            binding.fab.setOnClickListener {
                val intent = Intent(context, AddNoteActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appBarCallback = context as MainActivity
    }
}