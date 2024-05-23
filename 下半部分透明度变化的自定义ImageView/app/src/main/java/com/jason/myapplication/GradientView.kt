package com.jason.myapplication

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.ColorUtils
import kotlin.math.min

class GradientView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val colors = intArrayOf(
        0xFFAC6CFF.toInt(),
        0xFF7CA8FF.toInt(),
        0xFF409FFF.toInt(),
        0xFF99FFEE.toInt(),
        0xFF4DA6FF.toInt(),
        0xFF80AAFF.toInt(),
        0xFFCE69FF.toInt(),
        0xFFFFA9D5.toInt()
    )

    private val paint = Paint()
    private val linePaint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Draw the custom gradient strip
        drawCustomGradientStrip(canvas, colors)
    }

    private fun drawCustomGradientStrip(canvas: Canvas, colors: IntArray) {
        val screenWidth = width.toFloat()
        val stripHeight = 20f
        val stripTop = 20f
        val stripBottom = stripTop + stripHeight
        val stripRect = RectF(0f, stripTop, screenWidth, stripBottom)

        val positions = FloatArray(colors.size) { i ->
            i.toFloat() / (colors.size - 1)
        }

        val gradient = LinearGradient(
            0f, 0f, screenWidth, 0f,
            colors, positions, Shader.TileMode.CLAMP
        )

        paint.shader = gradient
        canvas.drawRect(stripRect, paint)

        val rWidth = screenWidth / 16
        val height = screenWidth / 8
        val width = screenWidth / 4

        val segment1 = rWidth
        val segment2 = segment1 + width
        val segment3 = segment2 + rWidth
        val segment4 = segment3 + height
        val segment5 = segment4 + rWidth
        val segment6 = segment5 + width
        val segment7 = segment6 + rWidth


        // Drawing lines at the specified segments
        val segmentPositions = listOf(segment1, segment2, segment3, segment4, segment5, segment6, segment7)

        for (x in segmentPositions) {
            canvas.drawLine(x, stripTop, x, stripBottom, linePaint)
        }
    }
}