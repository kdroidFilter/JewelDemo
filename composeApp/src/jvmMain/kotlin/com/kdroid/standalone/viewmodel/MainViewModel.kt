package com.kdroid.standalone.viewmodel

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import com.kdroid.jeweldemo.App
import com.kdroid.standalone.IntUiThemes
import com.kdroid.standalone.StandaloneSampleIcons
import com.kdroid.standalone.view.ComponentsView
import com.kdroid.standalone.view.MarkdownDemo
import com.kdroid.standalone.view.WelcomeView
import org.jetbrains.jewel.samples.standalone.viewmodel.KeyBinding
import org.jetbrains.jewel.samples.standalone.viewmodel.ViewInfo
import org.jetbrains.jewel.ui.icons.AllIconsKeys

object MainViewModel {
    fun onNavigateTo(destination: String) {
        currentView = views.first { viewInfo -> viewInfo.title == destination }
    }

    var theme: IntUiThemes by mutableStateOf(IntUiThemes.Light)

    var swingCompat: Boolean by mutableStateOf(false)

    val projectColor
        get() =
            if (theme.isLightHeader()) {
                Color(0xFFF5D4C1)
            } else {
                Color(0xFF654B40)
            }

    val views = mainMenuItems

    var currentView by mutableStateOf(views.first())
}

private val mainMenuItems =
    mutableStateListOf(
        ViewInfo(
            title = "Welcome",
            iconKey = StandaloneSampleIcons.welcome,
            keyboardShortcut = KeyBinding(macOs = setOf("⌥", "W"), windows = setOf("Alt", "W")),
            content = { WelcomeView() },
        ),
        ViewInfo(
            title = "Components",
            iconKey = StandaloneSampleIcons.componentsMenu,
            keyboardShortcut = KeyBinding(macOs = setOf("⌥", "C"), windows = setOf("Alt", "C")),
            content = { ComponentsView() },
        ),
        ViewInfo(
            title = "Markdown",
            iconKey = StandaloneSampleIcons.markdown,
            keyboardShortcut =
            KeyBinding(macOs = setOf("⌥", "M"), windows = setOf("Alt", "M"), linux = setOf("Alt", "M")),
            content = { MarkdownDemo() },
        ),
        ViewInfo(
            title = "ComposeMultiplateform",
            iconKey = AllIconsKeys.Actions.PreviewDetails,
            keyboardShortcut =
            KeyBinding(macOs = setOf("⌥", "N"), windows = setOf("Alt", "N"), linux = setOf("Alt", "N")),
            content = {
                CompositionLocalProvider(
                    LocalDensity provides Density(density = 0.75f, fontScale = 1.0f)
                ) {
                    App()
                }
            },
        ),
    )
