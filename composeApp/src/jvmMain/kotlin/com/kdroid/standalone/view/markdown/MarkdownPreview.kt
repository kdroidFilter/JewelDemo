package com.kdroid.standalone.view.markdown

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.jewel.foundation.ExperimentalJewelApi
import org.jetbrains.jewel.foundation.code.highlighting.NoOpCodeHighlighter
import org.jetbrains.jewel.foundation.theme.JewelTheme
import org.jetbrains.jewel.intui.markdown.standalone.ProvideMarkdownStyling
import org.jetbrains.jewel.intui.markdown.standalone.dark
import org.jetbrains.jewel.intui.markdown.standalone.light
import org.jetbrains.jewel.intui.markdown.standalone.styling.dark
import org.jetbrains.jewel.intui.markdown.standalone.styling.extensions.github.alerts.dark
import org.jetbrains.jewel.intui.markdown.standalone.styling.extensions.github.alerts.light
import org.jetbrains.jewel.intui.markdown.standalone.styling.light
import org.jetbrains.jewel.markdown.LazyMarkdown
import org.jetbrains.jewel.markdown.MarkdownBlock
import org.jetbrains.jewel.markdown.extension.autolink.AutolinkProcessorExtension
import org.jetbrains.jewel.markdown.extensions.github.alerts.AlertStyling
import org.jetbrains.jewel.markdown.extensions.github.alerts.GitHubAlertProcessorExtension
import org.jetbrains.jewel.markdown.extensions.github.alerts.GitHubAlertRendererExtension
import org.jetbrains.jewel.markdown.processing.MarkdownProcessor
import org.jetbrains.jewel.markdown.rendering.MarkdownBlockRenderer
import org.jetbrains.jewel.markdown.rendering.MarkdownStyling
import org.jetbrains.jewel.ui.component.VerticallyScrollableContainer
import org.jetbrains.jewel.ui.component.scrollbarContentSafePadding
import java.awt.Desktop
import java.net.URI

@Composable
internal fun MarkdownPreview(modifier: Modifier = Modifier, rawMarkdown: CharSequence) {
    val isDark = JewelTheme.isDark

    val markdownStyling = remember(isDark) { if (isDark) MarkdownStyling.dark() else MarkdownStyling.light() }

    var markdownBlocks by remember { mutableStateOf(emptyList<MarkdownBlock>()) }
    val extensions = remember { listOf(GitHubAlertProcessorExtension, AutolinkProcessorExtension) }

    // We are doing this here for the sake of simplicity.
    // In a real-world scenario you would be doing this outside your Composables,
    // potentially involving ViewModels, dependency injection, etc.
    val processor = remember { MarkdownProcessor(extensions, editorMode = true) }

    LaunchedEffect(rawMarkdown) {
        // TODO you may want to debounce or drop on backpressure, in real usages. You should also
        // not do this
        //  in the UI to begin with.
        @Suppress("InjectDispatcher") // This should never go in the composable IRL
        markdownBlocks = withContext(Dispatchers.Default) { processor.processMarkdownDocument(rawMarkdown.toString()) }
    }

    val blockRenderer =
        remember(markdownStyling, extensions) {
            if (isDark) {
                MarkdownBlockRenderer.dark(
                    styling = markdownStyling,
                    rendererExtensions = listOf(GitHubAlertRendererExtension(AlertStyling.dark(), markdownStyling)),
                )
            } else {
                MarkdownBlockRenderer.light(
                    styling = markdownStyling,
                    rendererExtensions = listOf(GitHubAlertRendererExtension(AlertStyling.light(), markdownStyling)),
                )
            }
        }

    // Using the values from the GitHub rendering to ensure contrast
    val background = remember(isDark) { if (isDark) Color(0xff0d1117) else Color.White }

    ProvideMarkdownStyling(markdownStyling, blockRenderer, NoOpCodeHighlighter) {
        val lazyListState = rememberLazyListState()
        VerticallyScrollableContainer(lazyListState, modifier.background(background)) {
            LazyMarkdown(
                markdownBlocks = markdownBlocks,
                modifier = Modifier.background(background),
                contentPadding =
                PaddingValues(start = 8.dp, top = 8.dp, end = 8.dp + scrollbarContentSafePadding(), bottom = 8.dp),
                state = lazyListState,
                selectable = true,
                onUrlClick = { url -> Desktop.getDesktop().browse(URI.create(url)) },
            )
        }
    }
}

