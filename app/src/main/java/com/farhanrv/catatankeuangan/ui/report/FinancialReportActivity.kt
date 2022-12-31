package com.farhanrv.catatankeuangan.ui.report

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.farhanrv.catatankeuangan.core.domain.model.CategoryNominal
import com.farhanrv.catatankeuangan.core.domain.model.CustomPieEntry
import com.farhanrv.catatankeuangan.databinding.ActivityFinancialReportBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import org.koin.androidx.viewmodel.ext.android.viewModel

class FinancialReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinancialReportBinding
    private val viewModel: FinancialReportViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFinancialReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Laporan Keuangan"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val expenseAdapter = FinancialReportAdapter()
        val incomeAdapter = FinancialReportAdapter()

        viewModel.notes.observe(this) { notes ->

            val entries: MutableList<PieEntry> = ArrayList()
            val customEntries = ArrayList<CustomPieEntry>()

            val dateList = notes.distinctBy { it.date }
            val kategoriList = notes.distinctBy { it.kategori }

            for (itemByKategori in kategoriList) {
                // filter data per kategori
                val extracted = notes.filter { it.kategori == itemByKategori.kategori }
                var nominal = 0
                for (extractedItem in extracted) {
                    nominal += extractedItem.nominal.toInt()
                }
                // TODO : add to entries
                customEntries.add(CustomPieEntry(nominal, itemByKategori.kategori, itemByKategori.jenis))
            }

            // total pengeluaran
            val customEntriesPengeluaran = customEntries.filter { it.jenis == "Pengeluaran" }
            var sumCustomEntriesPengeluaran = 0
            for (item in customEntriesPengeluaran) {
                sumCustomEntriesPengeluaran += item.num
            }

            // total pemasukan
            val customEntriesPemasukan = customEntries.filter { it.jenis == "Pemasukan" }
            var sumCustomEntriesPemasukan = 0
            for (item in customEntriesPemasukan) {
                sumCustomEntriesPemasukan += item.num
            }

            // add to entries
            for (item in customEntriesPengeluaran) {
                entries.add(PieEntry(divideSum(item.num, sumCustomEntriesPengeluaran), item.label))
            }

            Log.e("entries", entries.toString())

            val colors: ArrayList<Int> = ArrayList()
            for (color in ColorTemplate.MATERIAL_COLORS) {
                colors.add(color)
            }

            val set = PieDataSet(entries, "Laporan Keuangan")
            set.colors = colors
            val data = PieData(set)
            data.setValueFormatter(PercentFormatter(binding.chart))
            data.setValueTextSize(12f)
            binding.chart.data = data
            binding.chart.invalidate() // refresh
            binding.chart.animateY(1400, Easing.EaseInOutQuad)

            // set adapter recyclerView
            if (notes != null) {
                val sumNominalByKategori = ArrayList<CategoryNominal>()
                val filtered = notes.distinctBy { it.kategori }
                for (data in filtered) {
                    var sum = 0
                    for (noteByKategory in notes.filter { it.kategori == data.kategori }) {
                        sum += noteByKategory.nominal.toInt()
                    }
                    sumNominalByKategori.add(CategoryNominal(data.kategoriLogo, data.kategori, data.jenis, sum))
                }
                expenseAdapter.setListNotes(sumNominalByKategori.filter { it.jenis == "Pengeluaran" })
                incomeAdapter.setListNotes(sumNominalByKategori.filter { it.jenis == "Pemasukan" })

                binding.rvExpense.setHasFixedSize(true)
                binding.rvExpense.adapter = expenseAdapter

                binding.rvIncome.setHasFixedSize(true)
                binding.rvIncome.adapter = incomeAdapter
            }
        }
    }

    private fun divideSum(tem: Int, sum: Int): Float {
        val divided = tem.toFloat()/sum.toFloat()
        return divided*100
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}