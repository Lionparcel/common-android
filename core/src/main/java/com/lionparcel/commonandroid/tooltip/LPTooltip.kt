package com.lionparcel.commonandroid.tooltip

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleObserver

@DslMarker
internal annotation class TooltipInlineDsl

@TooltipInlineDsl
inline fun createTooltip(
    context: Context,
    crossinline block: LPTooltip.Builder.() -> Unit
): LPTooltip = LPTooltip.Builder(context).apply(block).build()

class LPTooltip(
    private val context: Context,
    private val builder: Builder
) : LifecycleObserver {

    private val binding: FrameLayout = .inflate(LayoutInflater.from(context), null, false)

    @TooltipInlineDsl
    class Builder(private val context: Context) {

        fun build(): LPTooltip = LPTooltip(
            context = context,
            builder = this@Builder
        )
    }

}
