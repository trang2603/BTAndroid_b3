package com.demo.model

import android.util.Log


data class Triangle(val p1: Point, val p2: Point, val p3: Point)
data class Point(val x:Float, val y:Float)

object Geometry{
    fun isPointInTriangle(point: Point, triangle: Triangle ): Boolean {
        /*//hướng của point so với cạnh
        val d1 = sign(point, triangel.p1, triangel.p2)
        val d2 = sign(point, triangel.p1, triangel.p3)
        val d3 = sign(point, triangel.p2, triangel.p3)

        //check point nằm bên phải hay trái của cạnh tam giác
        //âm bên phải, dương nằm bên trái
        val hasNeg = (d1<0 || d2 <0 || d3<0)
        val hasPos = (d1>0 || d2 >0 || d3>0)

        Log.e("", "21 $hasPos")
        Log.e("", "22 $hasNeg")

        //point thuộc triangle nếu các giá trị định hướng cùng dấu
        //hasPos true || hasNeg true thì point thuộc triangle
        return !(hasPos && hasNeg)*/

        val x = point.x
        val y = point.y

        val x1 = triangle.p1.x
        val y1 = triangle.p1.y
        val x2 = triangle.p2.x
        val y2 = triangle.p2.y
        val x3 = triangle.p3.x
        val y3 = triangle.p3.y

        val denominator = (y2 - y3) * (x1 - x3) + (x3 - x2) * (y1 - y3)
        val a = ((y2 - y3) * (x - x3) + (x3 - x2) * (y - y3)) / denominator
        val b = ((y3 - y1) * (x - x3) + (x1 - x3) * (y - y3)) / denominator
        val c = 1 - a - b

        Log.e("Geometry", "a: $a, b: $b, c: $c")


        return a >= 0 && a <= 1 && b >= 0 && b <= 1 && c >= 0 && c <= 1

    }

    private fun sign(point: Point, triPoint1: Point, triPoint2: Point): Float {
        return (point.x-triPoint2.x)*(triPoint1.y-triPoint2.y) - (triPoint1.x-triPoint2.x)*(point.y-triPoint2.y)
    }
}
