package com.farhanrv.catatankeuangan.ui.manage.category

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.farhanrv.catatankeuangan.ui.manage.category.expense.ExpenseFragment
import com.farhanrv.catatankeuangan.ui.manage.category.income.IncomeFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }
    override fun createFragment(position: Int): Fragment {
        var halaman: Fragment? = null
        when (position) {
            0 -> halaman = ExpenseFragment()
            1 -> halaman = IncomeFragment()
        }
        return halaman as Fragment
    }
}