package com.chuckerteam.chucker.internal.support

import android.graphics.Rect
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding

/**
 * Applies the system bar and display cutout window insets as padding on top of the view's
 * initial padding. This keeps content clear of the status bar, navigation bar and cutouts
 * once the window is drawing edge-to-edge, while preserving any padding declared in XML.
 */
internal fun View.applyWindowInsetsAsPadding(
    left: Boolean = false,
    top: Boolean = false,
    right: Boolean = false,
    bottom: Boolean = false
) {
    val initialPadding = Rect(paddingLeft, paddingTop, paddingRight, paddingBottom)
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, windowInsets ->
        val insets = windowInsets.getInsets(
            WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
        )
        view.updatePadding(
            left = initialPadding.left + (if (left) insets.left else 0),
            top = initialPadding.top + (if (top) insets.top else 0),
            right = initialPadding.right + (if (right) insets.right else 0),
            bottom = initialPadding.bottom + (if (bottom) insets.bottom else 0)
        )
        windowInsets
    }
    if (isAttachedToWindow) {
        ViewCompat.requestApplyInsets(this)
    }
}
