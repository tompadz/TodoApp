package com.dapadz.todoapp.ui.decorators

import android.graphics.*
import android.view.View
import androidx.core.view.children
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.dapadz.todoapp.utils.AndroidUtils.Companion.dp
import com.dapadz.todoapp.utils.AndroidUtils.Companion.dpf

class TaskBackgroundItemDecorator : RecyclerView.ItemDecoration() {

    private val offset = 16.dp()
    private val radius = 10.dpf()

    override fun onDraw(c : Canvas, parent : RecyclerView, state : RecyclerView.State) {
        c.save()
        parent.children.forEach { view ->
            val position = parent.getChildAdapterPosition(view).let {
                if (it == RecyclerView.NO_POSITION) return else it
            }
            if (parent.childCount == 1) {
                drawBox(
                    topR = radius,
                    bottomR = radius,
                    view = view,
                    c = c
                )
                return@forEach
            }
            when (position) {
                0 -> {
                    drawBox(
                        topR = radius,
                        view = view,
                        c = c
                    )
                }
                parent.childCount - 1 -> {
                    drawBox(
                        bottomR = radius,
                        view = view,
                        c = c
                    )
                }
                else -> {
                    drawBox(
                        view = view,
                        c = c
                    )
                }
            }
        }
        c.restore()
    }

    private fun drawBox(
        topR: Float = 0f,
        bottomR: Float = 0f,
        view: View,
        c: Canvas,
    ) {
        val ty = (view.translationY + 0.5f)
        val paint = Paint().apply {
            color = Color.WHITE
        }
        val rect = RectF(
            offset.toFloat(),
            view.top.toFloat() + ty,
            view.right.toFloat(),
            view.bottom.toFloat() + ty
        )
        val corners = floatArrayOf(
            topR, topR,
            topR, topR,
            bottomR, bottomR,
            bottomR, bottomR
        )
        val path = Path().apply {
            addRoundRect(rect, corners,Path.Direction.CW)
        }
        c.drawPath(path, paint)
    }

    override fun getItemOffsets(
        outRect : Rect,
        view : View,
        parent : RecyclerView,
        state : RecyclerView.State
    ) {
        view.setPadding(offset)
        with(outRect) {
            left = offset
            right = offset
            bottom = offset / 4
        }
    }
}