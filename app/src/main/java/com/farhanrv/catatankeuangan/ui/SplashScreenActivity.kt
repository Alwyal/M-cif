package com.farhanrv.catatankeuangan.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.farhanrv.catatankeuangan.R
import com.farhanrv.catatankeuangan.core.data.source.local.CategoryList
import com.farhanrv.catatankeuangan.databinding.ActivitySplashScreenBinding
import com.farhanrv.catatankeuangan.ui.home.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private val activityScope = CoroutineScope(Dispatchers.Main)

    private lateinit var binding: ActivitySplashScreenBinding
    private val viewModel: SplashScreenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        Glide.with(this)
            .load(com.farhanrv.catatankeuangan.assets.R.drawable.logo)
            .into(binding.imgSplashIcon)
        binding.imgSplashIcon.animation = fadeIn

        viewModel.category.observe(this) { data ->
            if (data.isEmpty()) {
                viewModel.insertCategory(CategoryList.categoryList())
            }
            activityScope.launch {

                val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }


    }
}