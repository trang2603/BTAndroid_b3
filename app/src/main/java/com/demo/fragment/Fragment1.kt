package com.demo.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.demo.R
import com.demo.databinding.Fragment1Binding
import com.demo.viewModel.CharacterCountViewModel

class Fragment1 : Fragment() {
    private lateinit var binding: Fragment1Binding
    private val viewModel: CharacterCountViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = Fragment1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCount.setOnClickListener {
            viewModel.characterCountCurrent(binding.edtText.text.toString())
        }
        viewModel.characterCounts.observe(viewLifecycleOwner, Observer { characterCounts ->
            binding.txtResults.text = characterCounts.joinToString("\n") { "Character: '${it.character}', Count: ${it.count}" }
        })
    }


}