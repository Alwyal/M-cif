package com.farhanrv.catatankeuangan.ui.manage.note.edit

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.farhanrv.catatankeuangan.R
import com.farhanrv.catatankeuangan.databinding.ActivityEditNoteBinding
import com.farhanrv.catatankeuangan.core.data.source.local.entity.NoteEntity
import com.farhanrv.catatankeuangan.core.domain.model.Note
import com.farhanrv.catatankeuangan.core.utils.DateConverters
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditNoteBinding
    private val viewModel: EditNoteViewModel by viewModel()
    private var id: Int = 0
    private var dateLong: Long = 0
    private var kategoriLogo: Int = com.farhanrv.catatankeuangan.assets.R.drawable.ic_category_antenna

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Edit"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intentExtra = intent.getParcelableExtra<Note>(EXTRA_DATA)

        binding.etTanggal.setOnClickListener {
            showDatePickerDialog()
        }

        val jenisItems = listOf("Pengeluaran", "Pemasukan")

        intentExtra?.let {
            with(binding){
                etTanggal.setText(DateConverters.convertLongToTime(intentExtra.date))
                etJenis.setText(intentExtra.jenis)
                etKategori.setText(intentExtra.kategori)
                etNominal.setText(intentExtra.nominal)
                etJudul.setText(intentExtra.title)
            }
            id = intentExtra.id
            dateLong = intentExtra.date
            kategoriLogo = intentExtra.kategoriLogo
        }
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

            (binding.tilJenis.editText as? AutoCompleteTextView)?.setAdapter(jenisAdapter)
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
            val newNote = NoteEntity(
                title = binding.etJudul.text.toString(),
                jenis = binding.etJenis.text.toString(),
                kategori = binding.etKategori.text.toString(),
                nominal = binding.etNominal.text.toString(),
                kategoriLogo = kategoriLogo,
                date = dateLong,
                id = id
            )
            viewModel.updateNote(newNote)
            Log.e("buttonSimpanClicked", newNote.toString())
            Toast.makeText(this@EditNoteActivity, "Data Berhasil Diubah", Toast.LENGTH_SHORT)
                .show()
            finish()
        }
    }

    private fun showDatePickerDialog() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Pilih tanggal")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        datePicker.addOnPositiveButtonClickListener {
            binding.etTanggal.setText("" + DateConverters.convertLongToTime(it))
            dateLong = it
        }

        datePicker.show(supportFragmentManager, "DatePicker")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}