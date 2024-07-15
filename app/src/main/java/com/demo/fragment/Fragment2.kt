package com.demo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.demo.databinding.Fragment2Binding
import com.demo.model.Point
import com.demo.model.Triangle
import com.demo.viewModel.TrianglePointViewModel

class Fragment2 :
    Fragment(),
    TriangleView.TriangleTouchListener {
    private lateinit var binding: Fragment2Binding
    val viewModel: TrianglePointViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = Fragment2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.triangleView.setTriangleTouchListener(this)

        viewModel.triangle.observe(
            viewLifecycleOwner,
            Observer { triangle ->
                binding.triangleView.setTriangle(triangle)
            },
        )

        viewModel.point.observe(
            viewLifecycleOwner,
            Observer { point ->
                binding.triangleView.setPoint(point)
            },
        )
        viewModel.isPointInTriangel.observe(
            viewLifecycleOwner,
            Observer { isInTriangle ->
                displayMessage(isInTriangle) //viewModel.point.value!!)
            },
        )

        val triangle =
            Triangle(
                Point(300f, 300f),
                Point(600f, 600f),
                Point(900f, 300f),
            )
        viewModel.setTriangle(triangle)
    }

    private fun displayMessage(inTraingle: Boolean/*, point: Point*/) {
        val message =
            if (inTraingle) {
                "Điểm A nằm bên trong tam giác"
            } else {
                "Điểm A không nằm bên trong tam giác"
            }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onTouch(point: Point) {
        viewModel.setPoint(point)
    }
}
