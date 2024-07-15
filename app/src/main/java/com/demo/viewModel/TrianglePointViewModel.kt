package com.demo.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.model.Geometry
import com.demo.model.Point
import com.demo.model.Triangle

class TrianglePointViewModel: ViewModel() {
    private val _triangle = MutableLiveData<Triangle>()
    val triangle: LiveData<Triangle> get() = _triangle

    private val _point = MutableLiveData<Point>()
    val point: LiveData<Point> get() = _point

    private val _isPointInTriangle = MutableLiveData<Boolean>()
    val isPointInTriangel: LiveData<Boolean> get() = _isPointInTriangle

    fun setTriangle(triangle: Triangle) {
        _triangle.value = triangle
        /*checkPointIntTriangle()*/
    }


    fun setPoint(point: Point) {
        _point.value = point
        checkPointIntTriangle()
        Log.e("", "29 kiểm tra point hoàn thành")
    }

   fun checkPointIntTriangle() {
        val tri = _triangle.value
        val p = _point.value
        if(p != null && tri != null) {
            _isPointInTriangle.value = Geometry.isPointInTriangle(p, tri)
        }
    }
}