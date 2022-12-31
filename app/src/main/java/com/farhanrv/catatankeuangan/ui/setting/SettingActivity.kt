package com.farhanrv.catatankeuangan.ui.setting

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.farhanrv.catatankeuangan.R
import org.json.JSONException
import org.json.JSONObject
import android.widget.TextView
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.farhanrv.catatankeuangan.databinding.ActivitySettingBinding
import org.json.JSONArray

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding
    var k1: TextView? = null
    var k2: TextView? = null
    var k3: TextView? = null
    var k4: TextView? = null
    var k5: TextView? = null
    var k6: TextView? = null
    var k7: TextView? = null
    var k8: TextView? = null
    var k9: TextView? = null
    var k10: TextView? = null
    var k11: TextView? = null
    var k12: TextView? = null
    var k13: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Nilai Tukar Rupiah (IDR)"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        k1 = findViewById(R.id.kurs1)
        k2 = findViewById(R.id.kurs2)
        k3 = findViewById(R.id.kurs3)
        k4 = findViewById(R.id.kurs4)
        k5 = findViewById(R.id.kurs5)
        k6 = findViewById(R.id.kurs6)
        k7 = findViewById(R.id.kurs7)
        k8 = findViewById(R.id.kurs8)
        k9 = findViewById(R.id.kurs9)
        k10 = findViewById(R.id.kurs10)
        k11 = findViewById(R.id.kurs11)
        k12 = findViewById(R.id.kurs12)
        k13 = findViewById(R.id.kurs13)
        tampilData()
    }


    private fun tampilData() {

        val queue = Volley.newRequestQueue(this)
        val url =
            "https://coba-50fc4-default-rtdb.firebaseio.com/-NKYSKob61NxjdJCR2hE.json?auth=H6zewSthT7DrjbJplsbpmEoybmQy0i4r0COqOhGK"
        val jsonObject = JSONObject()
        val requestBody = jsonObject.toString()
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                try {
                    val jo = JSONArray(response)
                    Log.d("kurs1", jo.getJSONObject(0).getString("kurs"))
                    val kurs1 = jo.getJSONObject(0).getString("kurs")
                    k1!!.text = kurs1

                    Log.d("kurs2", jo.getJSONObject(1).getString("kurs"))
                    val kurs2 = jo.getJSONObject(1).getString("kurs")
                    k2!!.text = kurs2

                    Log.d("kurs3", jo.getJSONObject(2).getString("kurs"))
                    val kurs3 = jo.getJSONObject(2).getString("kurs")
                    k3!!.text = kurs3

                    Log.d("kurs4", jo.getJSONObject(3).getString("kurs"))
                    val kurs4 = jo.getJSONObject(3).getString("kurs")
                    k4!!.text = kurs4

                    Log.d("kurs5", jo.getJSONObject(4).getString("kurs"))
                    val kurs5 = jo.getJSONObject(4).getString("kurs")
                    k5!!.text = kurs5

                    Log.d("kurs6", jo.getJSONObject(5).getString("kurs"))
                    val kurs6 = jo.getJSONObject(5).getString("kurs")
                    k6!!.text = kurs6

                    Log.d("kurs7", jo.getJSONObject(6).getString("kurs"))
                    val kurs7 = jo.getJSONObject(6).getString("kurs")
                    k7!!.text = kurs7

                    Log.d("kurs8", jo.getJSONObject(7).getString("kurs"))
                    val kurs8 = jo.getJSONObject(7).getString("kurs")
                    k8!!.text = kurs8

                    Log.d("kurs9", jo.getJSONObject(8).getString("kurs"))
                    val kurs9 = jo.getJSONObject(8).getString("kurs")
                    k9!!.text = kurs9

                    Log.d("kurs10", jo.getJSONObject(9).getString("kurs"))
                    val kurs10 = jo.getJSONObject(9).getString("kurs")
                    k10!!.text = kurs10

                    Log.d("kurs11", jo.getJSONObject(10).getString("kurs"))
                    val kurs11 = jo.getJSONObject(10).getString("kurs")
                    k11!!.text = kurs11

                    Log.d("kurs12", jo.getJSONObject(11).getString("kurs"))
                    val kurs12 = jo.getJSONObject(11).getString("kurs")
                    k12!!.text = kurs12

                    Log.d("kurs13", jo.getJSONObject(12).getString("kurs"))
                    val kurs13 = jo.getJSONObject(12).getString("kurs")
                    k13!!.text = kurs13

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        ) { error ->

            Toast.makeText(
                this@SettingActivity,
                "Gagal mengambil Rest Api$error",
                Toast.LENGTH_SHORT
            ).show()
        }
        queue.add(stringRequest)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}

//    private lateinit var binding: ActivitySettingBinding

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivitySettingBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        supportActionBar?.title = "Pengaturan"
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == android.R.id.home) {
//            finish()
//        }
//        return super.onOptionsItemSelected(item)
//    }
