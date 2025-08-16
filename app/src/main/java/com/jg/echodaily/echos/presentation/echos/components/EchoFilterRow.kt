package com.jg.echodaily.echos.presentation.echos.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.jg.echodaily.R
import com.jg.echodaily.core.presentation.desingsystem.chips.MultiChoiceChip
import com.jg.echodaily.core.presentation.desingsystem.dropdowns.Selectable
import com.jg.echodaily.core.presentation.desingsystem.dropdowns.SelectableDropDownOptionsMenu
import com.jg.echodaily.core.presentation.util.UIText
import com.jg.echodaily.echos.presentation.echos.EchosAction
import com.jg.echodaily.echos.presentation.echos.models.EchoFilterChip
import com.jg.echodaily.echos.presentation.echos.models.MoodChipContent
import com.jg.echodaily.echos.presentation.models.MoodUI

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EchoFilterRow(
    moodChipContent: MoodChipContent,
    hasActiveMoodFilters:Boolean,
    selectedEchoFilterChip:EchoFilterChip?,
    moods:List<Selectable<MoodUI>>,
    hasActiveTopicFilters:Boolean,
    topicChipTitle:UIText,
    topics:List<Selectable<String>>,
    onAction:(EchosAction) -> Unit,
    modifier: Modifier = Modifier

) {
    val context = LocalContext.current


    var dropDownOffset by remember {
        mutableStateOf(IntOffset.Zero)
    }

    val configuration = LocalConfiguration.current
    val dropDownMaxHeight = (configuration.screenHeightDp * 0.3f).dp
    FlowRow(
        modifier = modifier
            .padding(16.dp)
            .onGloballyPositioned {
                dropDownOffset = IntOffset(
                    x = 0,
                    y = it.size.height
                )
            },
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        MultiChoiceChip(
            displayText = moodChipContent.title.asString(),
            onClick = {
                onAction(EchosAction.OnMoodChipClick)
            },
            leadingContent = {
                if(moodChipContent.iconsRes.isNotEmpty()){
                    Row(
                        horizontalArrangement = Arrangement.spacedBy((-4).dp),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        moodChipContent.iconsRes.forEach{iconRes ->
                            Image(
                                imageVector = ImageVector.vectorResource(iconRes),
                                contentDescription = moodChipContent.title.asString(),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            },
            isClearVisible = hasActiveMoodFilters,
            isDropDownVisible = selectedEchoFilterChip == EchoFilterChip.MOODS,
            isHighlighted = hasActiveTopicFilters || selectedEchoFilterChip == EchoFilterChip.MOODS,
            onClearButtonClick = {
                onAction(EchosAction.OnRemoveFilters(EchoFilterChip.MOODS))
            },
            dropDownMenu = {
                SelectableDropDownOptionsMenu(
                    items = moods,
                    itemDisplayText = {moodUi -> moodUi.title.asString(context)},
                    onDismiss = {
                        onAction(EchosAction.OnDismissMoodDropDown)
                    },
                    key = {
                        moodsUi-> moodsUi.title.asString(context)
                    },
                    onItemClick = {moodUi ->
                        onAction(EchosAction.OnFilterByMoodClick(moodUi.item))
                    },
                    dropDownOffset = dropDownOffset,
                    maxDropDownHeight = dropDownMaxHeight,
                    leadingIcon = { moodUI ->
                        Image(
                            imageVector = ImageVector.vectorResource(moodUI.iconSet.fill),
                            contentDescription = moodUI.title.asString(),

                        )

                    }
                )
            }
        )
        MultiChoiceChip(
            displayText = topicChipTitle.asString(),
            onClick = {
                onAction(EchosAction.OnTopicChipClick)
            },
            isClearVisible = hasActiveTopicFilters,
            isDropDownVisible = selectedEchoFilterChip == EchoFilterChip.TOPICS,
            isHighlighted = hasActiveTopicFilters || selectedEchoFilterChip == EchoFilterChip.TOPICS,
            onClearButtonClick = {
                onAction(EchosAction.OnRemoveFilters(EchoFilterChip.TOPICS))
            },
            dropDownMenu = {
                if(topics.isEmpty()){
                    SelectableDropDownOptionsMenu(
                        items = listOf(Selectable(
                            item = stringResource(R.string.you_don_t_have_any_topics_yet),
                            selected = false
                        )),
                        itemDisplayText = {it},
                        onDismiss = {
                            onAction(EchosAction.OnDismissTopicDropDown)
                        },
                        key = { it },
                        onItemClick = {},
                        dropDownOffset = dropDownOffset,
                        maxDropDownHeight = dropDownMaxHeight,
                        dropDownExtras = null
                    )
                }else {
                    SelectableDropDownOptionsMenu(
                        items = topics,
                        itemDisplayText = { topic -> topic },
                        onDismiss = {
                            onAction(EchosAction.OnDismissTopicDropDown)
                        },
                        key = { topic ->
                            topic
                        },
                        onItemClick = { topic ->
                            onAction(EchosAction.OnFilterByTopicClick(topic.item))
                        },
                        dropDownOffset = dropDownOffset,
                        maxDropDownHeight = dropDownMaxHeight,
                        leadingIcon = { topic ->
                            Image(
                                imageVector = ImageVector.vectorResource(R.drawable.hashtag),
                                contentDescription = topic
                            )

                        }
                    )
                }
            }
        )
    }

}