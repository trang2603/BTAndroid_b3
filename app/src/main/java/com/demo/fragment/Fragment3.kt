package com.demo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.demo.R
import com.demo.adapter.Fragment2Adapter
import com.demo.databinding.Fragment1Binding
import com.demo.databinding.Fragment3Binding
import com.demo.viewModel.UserProfileViewModel
import com.google.android.material.tabs.TabLayoutMediator

class Fragment3 : Fragment() {
    private lateinit var binding: Fragment3Binding
    private val viewModel: UserProfileViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = Fragment3Binding.inflate(inflater, container, false)
        binding.viewPager.adapter = Fragment2Adapter(this)
        setUpTabLayout()
        setupDataFromJson()
        return binding.root
    }

    private fun setupDataFromJson() {
        viewModel.userProfile.observe(viewLifecycleOwner, Observer { userProfile ->
            binding.fullName.text = userProfile.fullName
            binding.position.text = userProfile.position
        })
    }

    private fun setUpTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) {tab, pos ->
            tab.text = when(pos) {
                0 -> "Hành trình"
                1 -> "Hoạt động"
                else -> "Hành trình"
            }
        }.attach()
    }

}