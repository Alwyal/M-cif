package com.farhanrv.catatankeuangan.ui.manage.note.add

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.farhanrv.catatankeuangan.R
import com.farhanrv.catatankeuangan.databinding.ActivityAddNoteBinding
import com.farhanrv.catatankeuangan.core.data.source.local.entity.NoteEntity
import com.farhanrv.catatankeuangan.core.utils.DateConverters
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private val viewModel: AddNoteViewModel by viewModel()
    private var dateLong: Long = 0
    private var kategoriLogo: Int = com.farhanrv.catatankeuangan.assets.R.drawable.ic_category_antenna

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etTanggal.setOnClickListener {
            showDatePickerDialog()
        }

        supportActionBar?.title = "Tambah Catatan"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val jenisItems = listOf("Pengeluaran", "Pemasukan")

        // filter kategory by pengeluaran / pemasukan
        viewModel.category.observe(this) { category ->
            val kategoriPengeluaran = category.filter { it.jenis == "Pengeluaran" }
            val kategoriPemasukan = category.filter { it.jenis == "Pemasukan" }

            val finalKategoriPengeluaran = ArrayList<String>()
            kategoriPengeluaran.forEach { data ->
                finalKategoriPengeluaran.add(data.name)
            }
            val finalKategoriPemasukan = ArrayList<String>()
            kategoriPemasukan.forEach { data ->
                finalKategoriPemasukan.add(data.name)
            }

            val jenisAdapter = ArrayAdapter(this, R.layout.dropdown_list_item, jenisItems)
            val pengeluaranAdapter =
                ArrayAdapter(this, R.layout.dropdown_list_item, finalKategoriPengeluaran)
            val pemasukanAdapter =
                ArrayAdapter(this, R.layout.dropdown_list_item, finalKategoriPemasukan)

            binding.etJenis.setText(jenisItems[0], false)
            (binding.tilJenis.editText as? AutoCompleteTextView)?.setAdapter(jenisAdapter)
            binding.etKategori.setText(kategoriPengeluaran[0].name, false)
            (binding.tilKategori.editText as? AutoCompleteTextView)?.setAdapter(pengeluaranAdapter)

            binding.etKategori.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    val arrayKategoriFiltered = category.filter { it.name == p0.toString() }
                    val kategoriChanged = arrayKategoriFiltered[0]
                    kategoriLogo = kategoriChanged.logoId
                }

            })

            binding.etJenis.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    if (p0.toString() == "Pengeluaran") {
                        binding.etKategori.setText(kategoriPengeluaran[0].name, false)
                        (binding.tilKategori.editText as? AutoCompleteTextView)?.setAdapter(
                            pengeluaranAdapter
                        )
                    } else if (p0.toString() == "Pemasukan") {
                        binding.etKategori.setText(kategoriPemasukan[0].name, false)
                        (binding.tilKategori.editText as? AutoCompleteTextView)?.setAdapter(
                            pemasukanAdapter
                        )
                    }
                }
            })
        }

        binding.buttonBatal.setOnClickListener {
            finish()
        }

        binding.buttonSimpan.setOnClickListener {
            val nominal = if (binding.etJenis.text.toString() == "Pengeluaran") {
                "-" + binding.etNominal.text.toString()
            } else {
                binding.etNominal.text.toString()
            }
            val newNote = NoteEntity(
                title = binding.etJudul.text.toString(),
                jenis = binding.etJenis.text.toString(),
                kategori = binding.etKategori.text.toString(),
                nominal = nominal,
                kategoriLogo = kategoriLogo,
                date = dateLong
            )
            viewModel.insertNote(newNote)
            showToast("Data Berhasil Ditambahkan")
            finish()
        }
    }

    private fun showDatePickerDialog() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Pilih Tanggal")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        datePicker.addOnPositiveButtonClickListener {
            binding.etTanggal.setText("" + DateConverters.convertLongToTime(it))
            dateLong = it
        }

        datePicker.show(supportFragmentManager, "DatePicker")
    }

    private fun showToast(text: String) {
        Toast.makeText(this@AddNoteActivity, text, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}