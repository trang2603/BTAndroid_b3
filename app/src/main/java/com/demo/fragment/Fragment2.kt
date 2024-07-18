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
    private val points = mutableListOf<Point>()
    private var isTriangleCreated = false

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

        binding.resetTriangleButton.setOnClickListener {
            resetTriangle()
        }

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
    }

    private fun displayMessage(inTraingle: Boolean/*, point: Point*/) {
        val message =
            if (inTraingle) {
                binding.text.setText("Điểm vừa chọn nằm bên trong tam giác")
            } else {
                binding.text.setText("Điểm vừa chọn nằm bên ngoài tam giác")
            }
    }

    override fun onTouch(point: Point) {
        if(!isTriangleCreated) {
            points.add(point)
            if (points.size == 3) {
                val triangle = Triangle(points[0], points[1], points[2])
                viewModel.setTriangle(triangle)
                points.clear()
                isTriangleCreated = true
            }
        }
        else viewModel.setPoint(point)
    }
    private fun resetTriangle() {
        points.clear()
        isTriangleCreated = false
        viewModel.setTriangle(Triangle(Point(0f, 0f), Point(0f, 0f), Point(0f, 0f)))
        binding.triangleView.invalidate()
    }
}
