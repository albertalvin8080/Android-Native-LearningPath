package org.albert.x14_customviews.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_MOVE
import android.view.MotionEvent.ACTION_UP
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import org.albert.x14_customviews.R
import kotlin.math.atan2
import kotlin.math.roundToInt

class ColorDialView
// Using @JvmOverloads may make the Android Studio's Layout Editor not able to display the view correctly.
@JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    View(context, attrs, defStyle) {

    private var colors: ArrayList<Int> = arrayListOf(
        Color.RED, Color.BLUE, Color.GREEN, Color.DKGRAY, Color.YELLOW,
        Color.CYAN, Color.MAGENTA, Color.BLACK
    )

    private var paint = Paint().also {
        it.color = Color.BLUE
        it.isAntiAlias = true
    }
    private var noColorDrawable: Drawable? = null
    private var dialDrawable: Drawable? = null
    private var dialDiameter = toDP(100f)
    private var extraPadding = toDP(30f)
    private var tickSize = toDP(10f)
    private var angleBetweenColors = 0f

    private var scale = 1f
    private var tickSizeScaled = 0f
        get() = tickSize * scale
    private var scaleToFit = false

    private var totalLeftPadding = 0f
    private var totalTopPadding = 0f
    private var totalRightPadding = 0f
    private var totalBottomPadding = 0f

    private var horizontalSize = 0f
    private var verticalSize = 0f

    private var tickPositionVertical = 0f
    private var horizontalCenter = 0f
    private var verticalCenter = 0f

    private var dragStartX = 0f
    private var dragStartY = 0f
    private var snapAngle = 0f
    private var dragging = false
    private var selectedPosition = 0

    init {
        val typedValue = context.obtainStyledAttributes(attrs, R.styleable.ColorDialView)
        colors = typedValue.getTextArray(R.styleable.ColorDialView_colors)
            .map {
                Color.parseColor(it.toString())
            } as ArrayList

        extraPadding = typedValue.getDimension(
            R.styleable.ColorDialView_tickPadding,
            extraPadding
        )
        tickSize = typedValue.getDimension(
            R.styleable.ColorDialView_tickRadius,
            tickSize
        )
        dialDiameter = typedValue.getDimension(
            R.styleable.ColorDialView_dialDiameter,
            dialDiameter
        )
        scaleToFit = typedValue.getBoolean(
            R.styleable.ColorDialView_scaleToFit,
            scaleToFit
        )
        typedValue.recycle()

        dialDrawable = AppCompatResources.getDrawable(context, R.drawable.ic_dial)
            .also {
                it?.bounds = getCenteredBounds(dialDiameter)
                it?.setTint(Color.DKGRAY)
            }
        noColorDrawable =
            AppCompatResources.getDrawable(context, R.drawable.baseline_no_color_selected_24)
                .also {
                    it?.bounds = getCenteredBounds(tickSize, 2f)
                }
        colors.add(0, Color.TRANSPARENT)
        angleBetweenColors = 360f / colors.size
        refreshValues(true)
    }

    // Compound bit shifted integer values (contain size and mode).
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (scaleToFit) {
            refreshValues(withScale = false)
            val specWidth = MeasureSpec.getSize(widthMeasureSpec)
            val specHeight = MeasureSpec.getSize(heightMeasureSpec)
            val workingWidth = specWidth - paddingLeft - paddingRight
            val workingHeight = specHeight - paddingTop - paddingBottom
            // Takes the lowest scale
            scale = if (workingWidth < workingHeight)
                workingWidth / (horizontalSize - paddingLeft - paddingRight)
            else
                workingHeight / (verticalSize - paddingBottom - paddingTop)
            dialDrawable?.let {
                it.bounds = getCenteredBounds((dialDiameter * scale))
            }
            noColorDrawable?.let {
                it.bounds = getCenteredBounds((tickSize * scale), 2f)
            }
            val width = resolveSizeAndState((horizontalSize * scale).toInt(), widthMeasureSpec, 0)
            val height = resolveSizeAndState((verticalSize * scale).toInt(), heightMeasureSpec, 0)
            refreshValues(true)
            setMeasuredDimension(width, height)
        } else {
            val width = resolveSizeAndState(horizontalSize.toInt(), widthMeasureSpec, 0)
            val height = resolveSizeAndState(verticalSize.toInt(), heightMeasureSpec, 0)
            setMeasuredDimension(width, height)
        }
    }

    override fun onDraw(canvas: Canvas) {
        val saveCount = canvas.save()
        colors.forEachIndexed { index, color ->
            if (index == 0) {
                val save = canvas.save()
                canvas.translate(horizontalCenter, tickPositionVertical)
                noColorDrawable?.draw(canvas)
                canvas.restoreToCount(save)
            } else {
                paint.color = color
                canvas.drawCircle(
                    horizontalCenter,
                    tickPositionVertical,
                    tickSizeScaled,
                    paint
                )
            }
            canvas.rotate(
                angleBetweenColors,
                horizontalCenter,
                verticalCenter
            )
        }
        canvas.restoreToCount(saveCount)
        canvas.rotate(snapAngle, horizontalCenter, verticalCenter)
        canvas.translate(horizontalCenter, verticalCenter)
        dialDrawable?.draw(canvas)
    }

    private fun refreshValues(withScale: Boolean = false) {
        val localScale = if (withScale) scale else 1f
        val scaledExtraPadding = extraPadding * localScale

        totalLeftPadding = paddingLeft + scaledExtraPadding
        totalTopPadding = paddingTop + scaledExtraPadding
        totalRightPadding = paddingRight + scaledExtraPadding
        totalBottomPadding = paddingBottom + scaledExtraPadding

        horizontalSize = dialDiameter + paddingLeft + paddingRight + (scaledExtraPadding * 2)
        verticalSize = dialDiameter + paddingTop + paddingBottom + (scaledExtraPadding * 2)

        tickPositionVertical = paddingTop + (scaledExtraPadding / 2f)
        horizontalCenter =
            totalLeftPadding + (horizontalSize - totalLeftPadding - totalRightPadding) / 2f
        verticalCenter =
            totalTopPadding + (verticalSize - totalTopPadding - totalBottomPadding) / 2f
    }

    private fun toDP(value: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value,
            context.resources.displayMetrics
        )
    }

    private fun getCenteredBounds(diameter: Float, localScale: Float = 1f): Rect {
        // Assumes it's a square.
        val half = ((if (diameter > 0) diameter / 2 else 1f) * localScale).toInt()
        return Rect(-half, -half, half, half)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        dragStartX = event.x
        dragStartY = event.y

        if (event.action == ACTION_DOWN || event.action == ACTION_MOVE) {
            dragging = true
            if (getSnapAngle(dragStartX, dragStartY)) {
                broadcastColorChange()
                invalidate() // redraw view
            }
        }

        if (event.action == ACTION_UP) {
            dragging = false
        }

        return true
    }

    private fun getSnapAngle(x: Float, y: Float): Boolean {
        var dragAngle = cartesianToPolar(
            x - horizontalSize / 2,
            (verticalSize - y) - verticalSize / 2
        )
        // colors[nearest]
        val nearest = (getNearestAngle(dragAngle) / angleBetweenColors).roundToInt()
        val newAngle: Float = nearest * angleBetweenColors
        var shouldUpdate = false
        if(newAngle != snapAngle) {
            shouldUpdate = true
            selectedPosition = nearest
        }
        snapAngle = newAngle
        return shouldUpdate
    }

    private fun getNearestAngle(angle: Float): Float {
        var adjustedAngle = (360 - angle) + 90
        while (adjustedAngle > 360) adjustedAngle -= 360
        return adjustedAngle
    }

    private fun cartesianToPolar(x: Float, y: Float): Float {
        val angle = Math.toDegrees(
            (atan2(y.toDouble(), x.toDouble()))
        ).toFloat()
        return when(angle) {
            in 0f..180f -> angle
            in -180f..0f -> angle + 360f
            else -> angle
        }
    }

    var selectedColor: Int = android.R.color.transparent
        set(value) {
            val idx = colors.indexOf(value)
            selectedPosition = if(idx == -1) 0 else idx
            snapAngle = (selectedPosition * angleBetweenColors)
            invalidate()
        }
    private val listeners = ArrayList<(Int) -> Unit>()

    fun addListener(callback: (Int) -> Unit) {
        listeners.add(callback)
    }

    private fun broadcastColorChange() {
        listeners.forEach { f ->
            if(selectedPosition > colors.size - 1) {
                f(colors[0])
                return@forEach
            }
            f(colors[selectedPosition])
        }
    }
}