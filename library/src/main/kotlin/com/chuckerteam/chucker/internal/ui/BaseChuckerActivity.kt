package com.chuckerteam.chucker.internal.ui

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.chuckerteam.chucker.internal.data.repository.RepositoryProvider

internal abstract class BaseChuckerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        RepositoryProvider.initialize(applicationContext)
    }

    private fun enableEdgeToEdge() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val isNightMode = resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
        WindowInsetsControllerCompat(window, window.decorView).apply {
            // The app bar is always the primary color with light content, so keep status bar
            // icons light regardless of theme.
            isAppearanceLightStatusBars = false
            // The navigation bar sits over the app background, so match its icons to the theme.
            isAppearanceLightNavigationBars = !isNightMode
        }
    }

    override fun onResume() {
        super.onResume()
        isInForeground = true
    }

    override fun onPause() {
        super.onPause()
        isInForeground = false
    }

    companion object {
        var isInForeground: Boolean = false
            private set
    }

    fun showToast(message: String, toastDuration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this.applicationContext, message, toastDuration).show()
    }
}
