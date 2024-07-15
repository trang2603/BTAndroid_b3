package com.demo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.demo.fragment.Fragment1
import com.demo.fragment.Fragment2
import com.demo.fragment.Fragment3

class FragmentAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> Fragment1()
            1 -> Fragment2()
            else -> Fragment3()
        }
    }
}