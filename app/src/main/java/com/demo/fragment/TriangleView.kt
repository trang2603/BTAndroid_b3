package com.demo.fragment

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.demo.model.Point
import com.demo.model.Triangle
import com.demo.viewModel.TrianglePointViewModel

class TriangleView @JvmOverloads constructor(context: Context, att: AttributeSet? = null) : View(context, att) {
    private val paintTriangel = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    private val paintPoint = Paint().apply {
        color = Color.CYAN
        style = Paint.Style.FILL
    }

    private lateinit var viewModel: TrianglePointViewModel
    private var triangel: Triangle? = null
    private var point: Point? = null
    private var isPointInTriangle: Boolean = false
    private var touchListener: TriangleTouchListener? = null

    fun setTriangle(triangle: Triangle) {
        this.triangel = triangle
        invalidate()
    }

    fun setPoint(point: Point) {
        this.point =point
//        this.isPointInTriangle = isInTriangle
        invalidate()
    }

    fun setTriangleTouchListener(listener: TriangleTouchListener) {
        this.touchListener = listener
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        triangel?.let {
            val pts = floatArrayOf(
                it.p1.x, it.p1.y, it.p2.x, it.p2.y,
                it.p3.x, it.p3.y, it.p2.x, it.p2.y,
                it.p1.x, it.p1.y, it.p3.x, it.p3.y,

            )
            canvas.drawLines(pts, paintTriangel)
        }
        point?.let {
            canvas.drawCircle(it.x, it.y, 10f, paintPoint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if((event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) && event != null) {
            val point = Point(event.x, event.y)
            touchListener?.onTouch(point)
            return true
        }
        return false
    }

    interface TriangleTouchListener {
        fun onTouch(point: Point)
    }
}