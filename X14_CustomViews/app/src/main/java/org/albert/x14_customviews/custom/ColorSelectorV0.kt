package org.albert.x14_customviews.custom

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import org.albert.x14_customviews.R

class ColorSelectorV0
@JvmOverloads
constructor(
    context: Context, attributeSet: AttributeSet? = null,
    defStyle: Int = 0, defRes: Int = 0
) : LinearLayout(context, attributeSet, defStyle, defRes) {

    private val colors = listOf(Color.BLUE, Color.RED, Color.GREEN)
    private var selectedIndex = 0

    init {
        orientation = HORIZONTAL
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
                as LayoutInflater
        // Inflate color_selector into this LinearLayout
        val view = inflater.inflate(R.layout.color_selector, this)

        val selectedColor = view.findViewById<View>(R.id.selectedColor)
        selectedColor.setBackgroundColor(colors[selectedIndex])

        val arrowBack = view.findViewById<ImageView>(R.id.arrowBack)
        val arrowForward = view.findViewById<ImageView>(R.id.arrowForward)

        setArrowTheme(arrowBack, arrowForward)

        arrowBack.setOnClickListener {
            previousColor(selectedColor)
        }

        arrowForward.setOnClickListener {
            nextColor(selectedColor)
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
    }

    private fun nextColor(view: View) {
        if (selectedIndex == colors.lastIndex)
            selectedIndex = 0
        else
            ++selectedIndex
        view.setBackgroundColor(colors[selectedIndex])
    }
}