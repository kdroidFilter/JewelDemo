package com.kdroid.standalone.viewmodel

import SplitLayouts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.kdroid.standalone.StandaloneSampleIcons
import com.kdroid.standalone.view.component.Borders
import com.kdroid.standalone.view.component.Buttons
import com.kdroid.standalone.view.component.Checkboxes
import com.kdroid.standalone.view.component.ChipsAndTrees
import com.kdroid.standalone.view.component.Dropdowns
import com.kdroid.standalone.view.component.Icons
import com.kdroid.standalone.view.component.Links
import com.kdroid.standalone.view.component.ProgressBar
import com.kdroid.standalone.view.component.RadioButtons
import com.kdroid.standalone.view.component.Scrollbars
import com.kdroid.standalone.view.component.SegmentedControls
import com.kdroid.standalone.view.component.Sliders
import com.kdroid.standalone.view.component.Tabs
import com.kdroid.standalone.view.component.TextAreas
import com.kdroid.standalone.view.component.TextFields
import com.kdroid.standalone.view.component.Tooltips
import org.jetbrains.jewel.samples.standalone.viewmodel.ViewInfo
import org.jetbrains.jewel.ui.component.SplitLayoutState

object ComponentsViewModel {
    private var outerSplitState by mutableStateOf(SplitLayoutState(0.5f))
    private var verticalSplitState by mutableStateOf(SplitLayoutState(0.5f))
    private var innerSplitState by mutableStateOf(SplitLayoutState(0.5f))

    val views: SnapshotStateList<ViewInfo> =
        mutableStateListOf(
            ViewInfo(title = "Buttons", iconKey = StandaloneSampleIcons.Components.button, content = { Buttons() }),
            ViewInfo(
                title = "Radio Buttons",
                iconKey = StandaloneSampleIcons.Components.radioButton,
                content = { RadioButtons() },
            ),
            ViewInfo(
                title = "Checkboxes",
                iconKey = StandaloneSampleIcons.Components.checkbox,
                content = { Checkboxes() },
            ),
            ViewInfo(
                title = "Dropdowns",
                iconKey = StandaloneSampleIcons.Components.comboBox,
                content = { Dropdowns() },
            ),
            ViewInfo(
                title = "Chips and trees",
                iconKey = StandaloneSampleIcons.Components.tree,
                content = { ChipsAndTrees() },
            ),
            ViewInfo(
                title = "Progressbar",
                iconKey = StandaloneSampleIcons.Components.progressBar,
                content = { ProgressBar() },
            ),
            ViewInfo(title = "Icons", iconKey = StandaloneSampleIcons.Components.toolbar, content = { Icons() }),
            ViewInfo(title = "Links", iconKey = StandaloneSampleIcons.Components.links, content = { Links() }),
            ViewInfo(title = "Borders", iconKey = StandaloneSampleIcons.Components.borders, content = { Borders() }),
            ViewInfo(
                title = "Segmented Controls",
                iconKey = StandaloneSampleIcons.Components.segmentedControls,
                content = { SegmentedControls() },
            ),
            ViewInfo(title = "Sliders", iconKey = StandaloneSampleIcons.Components.slider, content = { Sliders() }),
            ViewInfo(title = "Tabs", iconKey = StandaloneSampleIcons.Components.tabs, content = { Tabs() }),
            ViewInfo(title = "Tooltips", iconKey = StandaloneSampleIcons.Components.tooltip, content = { Tooltips() }),
            ViewInfo(
                title = "TextAreas",
                iconKey = StandaloneSampleIcons.Components.textArea,
                content = { TextAreas() },
            ),
            ViewInfo(
                title = "TextFields",
                iconKey = StandaloneSampleIcons.Components.textField,
                content = { TextFields() },
            ),
            ViewInfo(
                title = "Scrollbars",
                iconKey = StandaloneSampleIcons.Components.scrollbar,
                content = { Scrollbars() },
            ),
            ViewInfo(
                title = "SplitLayout",
                iconKey = StandaloneSampleIcons.Components.splitlayout,
                content = {
                    SplitLayouts(outerSplitState, verticalSplitState, innerSplitState) {
                        outerSplitState = SplitLayoutState(0.5f)
                        verticalSplitState = SplitLayoutState(0.5f)
                        innerSplitState = SplitLayoutState(0.5f)
                    }
                },
            ),
        )
    var currentView by mutableStateOf(views.first())
}
