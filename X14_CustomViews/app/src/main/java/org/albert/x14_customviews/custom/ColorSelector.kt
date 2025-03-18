package org.albert.x14_customviews.custom

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import org.albert.x14_customviews.R
import org.albert.x14_customviews.databinding.ColorSelectorBinding

class ColorSelector
@JvmOverloads
constructor(
    context: Context, attributeSet: AttributeSet? = null,
    defStyle: Int = 0, defRes: Int = 0
) : LinearLayout(context, attributeSet, defStyle, defRes) {

    private var colors = listOf(Color.BLUE, Color.RED, Color.GREEN)
    private var selectedIndex = 0

    private var binding: ColorSelectorBinding

    init {
        val typedArray = context.obtainStyledAttributes(
            attributeSet, R.styleable.ColorSelector
        )
        colors = typedArray.getTextArray(R.styleable.ColorSelector_colors)
            .map {
                Color.parseColor(it.toString()) // HEX to integer
            }
        typedArray.recycle()

        orientation = HORIZONTAL
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
                as LayoutInflater
        // Inflate color_selector into this LinearLayout
        binding = ColorSelectorBinding.inflate(inflater, this)

        val selectedColor = binding.selectedColor
        selectedColor.setBackgroundColor(colors[selectedIndex])

        val arrowBack = binding.arrowBack
        val arrowForward = binding.arrowForward

        setArrowTheme(arrowBack, arrowForward)

        arrowBack.setOnClickListener {
            previousColor(selectedColor)
        }

        arrowForward.setOnClickListener {
            nextColor(selectedColor)
        }

        binding.checkColorEnabled.setOnClickListener {
            broadcastColor()
        }
    }

    private fun setArrowTheme(arrowBack: ImageView, arrowForward: ImageView) {
        val nightModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            arrowBack.setImageResource(R.drawable.baseline_arrow_back_white_ios_24)
            arrowForward.setImageResource(R.drawable.baseline_arrow_forward_white_ios_24)
        } else {
            arrowBack.setImageResource(R.drawable.baseline_arrow_back_white_ios_24)
            arrowForward.setImageResource(R.drawable.baseline_arrow_forward_white_ios_24)
        }
    }

    private fun previousColor(view: View) {
        if (selectedIndex == 0)
            selectedIndex = colors.lastIndex
        else
            --selectedIndex
        view.setBackgroundColor(colors[selectedIndex])
        broadcastColor()
    }

    private fun nextColor(view: View) {
        if (selectedIndex == colors.lastIndex)
            selectedIndex = 0
        else
            ++selectedIndex
        view.setBackgroundColor(colors[selectedIndex])
        broadcastColor()
    }

    private fun broadcastColor() {
        val color = if (binding.checkColorEnabled.isChecked)
            colors[selectedIndex]
        else
            Color.TRANSPARENT
        Log.d(this::class.simpleName, "broadcast: $color")
        colorListeners.forEach { f -> f(color) }
    }

    var selectedColor: Int = android.R.color.transparent
        set(value) {
            val idx = colors.indexOf(value)
            selectedIndex =
                if (idx == -1) 0
                else idx
            binding.selectedColor.setBackgroundColor(colors[selectedIndex])
        }

    private val colorListeners = ArrayList<(Int) -> Unit>()

    fun addColorListener(callback: (Int) -> Unit) {
        colorListeners.add(callback)
    }
}