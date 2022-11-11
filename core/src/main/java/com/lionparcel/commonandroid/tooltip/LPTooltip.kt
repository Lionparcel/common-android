package com.lionparcel.commonandroid.tooltip

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.lionparcel.commonandroid.R

class LPTooltip(
    context: Context,
    parentActivity: Activity,
    themeStyleRes: Int = R.style.LPTooltipDialogTheme
): Dialog(context, themeStyleRes) {

    private val ttContentView: RelativeLayout
    private val ttContainer: RelativeLayout
    private val ttUpArrow: ImageView
    private val ttDownArrow: ImageView
    private val ttDialogBox: LinearLayout
    private val ttContent: TextView
    private val ttClose: ImageView
    private var windowHeight: Int
    private var windowWidth: Int
    private var statusBarHeight: Int

    private var content: String = ""

    init {
        setContentView(R.layout.lp_layout_tooltip_body)
        ttContentView = findViewById(R.id.ttContentView)
        ttContainer = findViewById(R.id.ttContainer)
        ttUpArrow = findViewById(R.id.ttTopArrow)
        ttDownArrow = findViewById(R.id.ttBottomArrow)
        ttDialogBox = findViewById(R.id.ttDialogBox)
        ttContent = findViewById(R.id.ttContent)
        ttClose = findViewById(R.id.ttClose)

        val usableView = parentActivity.window.findViewById<View>(Window.ID_ANDROID_CONTENT)
        windowHeight = usableView.height
        windowWidth = usableView.width
        statusBarHeight = getScreenHeight(context) - windowHeight

        window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        ttContentView.setOnClickListener {
            dismiss()
        }
        ttClose.setOnClickListener {
            dismiss()
        }
    }

    override fun show() {
        super.show()
    }

    private fun drawShade() {
        val bitmap = Bitmap.createBitmap(windowWidth, windowHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(ContextCompat.getColor(context, R.color.transparent))
        ttContentView.background = BitmapDrawable(context.resources, bitmap)
    }

    fun pointTo(x: Int, y: Int, position: Position = Position.AUTO) : LPTooltip {
        val params = ttContainer.layoutParams as RelativeLayout.LayoutParams

        adjustContainerMargin(x)

        if (position == Position.ABOVE || (position == Position.AUTO && y > windowHeight / 2 - statusBarHeight)) {
            // point is on the lower half of the screen, position dialog above
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
            params.bottomMargin = windowHeight - y - statusBarHeight + dpToPx(16F)
            if (x >= 0) {
                pointArrowTo(ttDownArrow, x)
            }
        } else {
            // point is on the upper half of the screen, position dialog below
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP)
            params.topMargin = y - statusBarHeight
            if (x >= 0) {
                pointArrowTo(ttUpArrow, x)
            }
        }

        ttContainer.layoutParams = params
        return this
    }

    private fun pointArrowTo(arrow: ImageView, x: Int) {
        val arrowParams = arrow.layoutParams as RelativeLayout.LayoutParams
        val arrowWidth = dpToPx(20F)
        if (x > windowWidth / 2) {
            arrowParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
            arrowParams.rightMargin = windowWidth - x - arrowWidth / 2
        } else {
            arrowParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
            arrowParams.leftMargin = x - arrowWidth / 2
        }
        arrow.layoutParams = arrowParams
        arrow.visibility = View.VISIBLE
    }

    private fun adjustContainerMargin(x: Int) {
        var leftMargin = context.resources.getDimension(R.dimen.spacing_s)
        var rightMargin = leftMargin
        if(x > windowWidth - windowWidth / 3) {
            leftMargin = 30f
            rightMargin = 0f
        } else if (x < windowWidth / 3) {
            leftMargin = 0f
            rightMargin = 30f
        }
        val params = ttContainer.layoutParams as RelativeLayout.LayoutParams
        params.leftMargin = dpToPx(leftMargin)
        params.rightMargin = dpToPx(rightMargin)
        ttContainer.layoutParams = params
    }

    fun content(content: String): LPTooltip {
        ttContent.text = content
        ttContent.visibility = View.VISIBLE
        this.content = content
        return this
    }

    fun closeIcon(): LPTooltip {
        ttClose.visibility = View.VISIBLE
        return this
    }

    private fun getScreenHeight(context: Context): Int {
        val wm =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val size = Point()
        wm.defaultDisplay.getSize(size)
        return size.y
    }

    private fun dpToPx(dp: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    enum class Position {
        AUTO, ABOVE, BELOW
    }

}
