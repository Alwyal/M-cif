package com.farhanrv.catatankeuangan.ui.manage.category.add

import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.farhanrv.catatankeuangan.R
import com.farhanrv.catatankeuangan.core.data.source.local.CategoryList
import com.farhanrv.catatankeuangan.core.data.source.local.entity.CategoryEntity
import com.farhanrv.catatankeuangan.core.domain.model.Category
import com.farhanrv.catatankeuangan.databinding.ActivityAddCategoryBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddCategoryActivity : AppCompatActivity(), AddCategoryCallback {

    private lateinit var binding: ActivityAddCategoryBinding
    private val viewModel: AddCategoryViewModel by viewModel()
    private var logoKategori: Int = 0
    private var categoryId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.elevation = 0F
        supportActionBar?.title = "Tambah Kategori"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val jenisItems = listOf("Pengeluaran", "Pemasukan")
        val jenisAdapter = ArrayAdapter(this, R.layout.dropdown_list_item, jenisItems)
        binding.etJenisKategori.setText(jenisItems[0], false)
        (binding.tilJenisKategori.editText as? AutoCompleteTextView)?.setAdapter(jenisAdapter)

        intent.getParcelableExtra<CategoryEntity>(EXTRA_DATA)?.let {
            supportActionBar?.title = "Edit Kategori"
            binding.etNamaKategori.setText(it.name)
            binding.etJenisKategori.setText(it.jenis, false)
            Glide.with(this)
                .load(it.logoId)
                .into(binding.imgIconSelected)
            logoKategori = it.logoId
            categoryId = it.id
        }

        val myAdapter = AddCategoryAdapter(this)

        myAdapter.setCategoryList(CategoryList.categoryIconList())

        val layoutManager = FlexboxLayoutManager(this)
        layoutManager.flexDirection = FlexDirection.ROW;
        layoutManager.justifyContent = JustifyContent.FLEX_START;
        binding.recyclerView.layoutManager = layoutManager

        binding.recyclerView.adapter = myAdapter

        binding.buttonSimpan.setOnClickListener {
            val name = binding.etNamaKategori.text.toString()
            val jenis = binding.etJenisKategori.text.toString()
            if (logoKategori != 0) {
                if (intent.getParcelableExtra<CategoryEntity>(EXTRA_DATA) == null) {
                    val newCategory = CategoryEntity(
                        name = name,
                        jenis = jenis,
                        logoId = logoKategori
                    )
                    viewModel.insertCategory(newCategory)
                } else {
                    val existingCategory = CategoryEntity(
                        name = name,
                        jenis = jenis,
                        logoId = logoKategori,
                        id = categoryId
                    )
                    viewModel.updateCategory(existingCategory)
                }
                finish()
            }
        }
        binding.buttonBatal.setOnClickListener {
            finish()
        }
    }

    override fun onIconClicked(category: Category) {
        Glide.with(this)
            .load(category.logo)
            .into(binding.imgIconSelected)
        logoKategori = category.logo
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_DATA = "extra data"
    }
}