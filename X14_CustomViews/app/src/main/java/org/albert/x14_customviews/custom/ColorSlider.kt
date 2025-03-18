package org.albert.x14_customviews.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.SeekBar
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.core.content.ContextCompat
import org.albert.x14_customviews.R

class ColorSlider
@JvmOverloads
constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = androidx.appcompat.R.attr.seekBarStyle
) : AppCompatSeekBar(context, attributeSet, defStyleAttr) {

    private var colors: ArrayList<Int> = arrayListOf(Color.BLUE, Color.RED, Color.GREEN)

    init {
        val typedArray = context.obtainStyledAttributes(attributeSet,
            R.styleable.ColorSlider)
        colors = typedArray.getTextArray(R.styleable.ColorSlider_colors)
            .map {
                Color.parseColor(it.toString())
            } as ArrayList<Int>
        typedArray.recycle()

        colors.add(0, Color.TRANSPARENT)

        max = colors.size - 1
        progressTintList = ContextCompat.getColorStateList(context,
            android.R.color.transparent)
        progressBackgroundTintList = ContextCompat.getColorStateList(context,
            android.R.color.transparent)
        splitTrack = false
        setPadding(
            paddingLeft,
            paddingTop,
            paddingRight,
            paddingBottom + getPixelValueFromDp(42f).toInt()
        )
        thumb = AppCompatResources.getDrawable(context, R.drawable.baseline_arrow_drop_down_24)

        setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                listeners.forEach {
                    it(colors[progress])
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawColors(canvas)
    }

    private fun getPixelValueFromDp(value: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
            value,
            context.resources.displayMetrics)
    }

    private fun drawColors(canvas: Canvas) {
        val count = colors.size
        val saveCount = canvas.save()
        // Centralizes the imaginary pen horizontally (for the first square)
        // and vertically to the view container. This avoids clipping.
        canvas.translate(
            paddingLeft.toFloat(),
            (height / 2).toFloat()
        )

        if (count > 1) {
            val w = getPixelValueFromDp(16f)
            val h = getPixelValueFromDp(16f)
            val halfW = w / 2
            val halfH = h / 2

            val spacing = (width - paddingLeft - paddingRight) / (count - 1).toFloat()

            for (i in 0 until count) {
                if (i == 0) {
                    val drawable = AppCompatResources.getDrawable(context, R.drawable.baseline_no_color_selected_24)
                    val w2 = drawable?.intrinsicWidth ?: 0
                    val h2 = drawable?.intrinsicHeight ?: 0
                    val halfW2 = w2 / 2
                    val halfH2 = h2 / 2
                    drawable?.setBounds(-halfW2, -halfH2, halfW2, halfH2)
                    drawable?.draw(canvas)
                }
                else {
                    val paint = Paint().apply {
                        color = this@ColorSlider.colors[i]
                    }
                    canvas.drawRect(-halfW, -halfH, halfW, halfH, paint)
                }
                canvas.translate(spacing, 0f) // dx, dy
            }
            // Translates the "imaginary pen" back to the beginning in case we want to draw more shapes.
            // This is necessary because we used canvas.translate(...) inside the loop.
            canvas.restoreToCount(saveCount)
        }
    }

    var selectedColor = android.R.color.transparent
        set(value) {
            val idx = colors.indexOf(value)
            val colorIdx =
                if(idx == -1) 0
                else idx
            progress = colorIdx
        }

    private val listeners = ArrayList<(Int) -> Unit>()

    fun addListener(callback: (Int) -> Unit) {
        listeners.add(callback)
    }
}