package com.farhanrv.catatankeuangan.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.farhanrv.catatankeuangan.R
import com.farhanrv.catatankeuangan.databinding.ActivityMainBinding
import com.farhanrv.catatankeuangan.ui.manage.category.CategoryActivity
import com.farhanrv.catatankeuangan.ui.report.FinancialReportActivity
import com.farhanrv.catatankeuangan.ui.setting.SettingActivity
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, AppBarCallback {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appBarMain.toolbar.setTitleTextAppearance(this, R.style.bold)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.appBarMain.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, HomeFragment())
                .commit()
        }

        binding.navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_kurs-> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_kategori -> {
                val intent = Intent(this, CategoryActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_laporan_keuangan -> {
                val intent = Intent(this, FinancialReportActivity::class.java)
                startActivity(intent)
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun changeSaldo(saldo: String) {
        binding.appBarMain.toolbar.title = "Saldo $saldo"
    }
}