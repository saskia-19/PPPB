package com.example.mycourse

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.mycourse.databinding.ActivityMateriBinding
import com.google.android.material.tabs.TabLayoutMediator

class MateriActivity : AppCompatActivity() {

    companion object {
        @StringRes
        private val TAB_TITLE = intArrayOf(
            R.string.tab_materi_1,
            R.string.tab_materi_2
        )
    }

    private lateinit var binding: ActivityMateriBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMateriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionPagerAdapter = SectionPagerAdapter(this)
        binding.viewPager.adapter = sectionPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLE[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }
}
