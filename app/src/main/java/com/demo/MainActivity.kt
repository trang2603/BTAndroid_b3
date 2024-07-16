package com.demo

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.demo.adapter.FragmentAdapter
import com.demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = FragmentAdapter(this)

        //thay đổi khi ckick vào icon của các fragment
        setupBottomNavClick()

        //thay đổi khi vuốt giữa các fragment
        setupViewPagerChange()
    }

    private fun setupViewPagerChange() {
        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bottomNav.menu.getItem(position).isChecked = true
                when(position) {
                    0 -> binding.bottomNav.menu.getItem(0).icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_home)
                    1 -> binding.bottomNav.menu.getItem(1).icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_draw)
                    2 -> binding.bottomNav.menu.getItem(2).icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_account)

                }
            }
        })
    }

    private fun setupBottomNavClick() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.Fragment1 -> {
                    binding.viewPager.currentItem = 0
                    true
                }
                R.id.Fragment2 -> {
                    binding.viewPager.currentItem = 1
                    true
                }
                R.id.Fragment3 -> {
                    binding.viewPager.currentItem = 2
                    true
                }
                else -> false
            }
        }
    }
}