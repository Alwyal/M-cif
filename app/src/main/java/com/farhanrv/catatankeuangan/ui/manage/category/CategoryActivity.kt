package com.farhanrv.catatankeuangan.ui.manage.category

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.farhanrv.catatankeuangan.R
import com.farhanrv.catatankeuangan.databinding.ActivityCategoryBinding
import com.farhanrv.catatankeuangan.ui.manage.category.add.AddCategoryActivity
import com.google.android.material.tabs.TabLayoutMediator

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.elevation = 0F
        supportActionBar?.title = "Kategori"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        binding.fab.setOnClickListener {
            val intent = Intent(this, AddCategoryActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.category_tab_text_1,
            R.string.category_tab_text_2
        )
    }
}