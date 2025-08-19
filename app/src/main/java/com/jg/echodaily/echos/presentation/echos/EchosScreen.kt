package com.jg.echodaily.echos.presentation.echos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jg.echodaily.core.presentation.desingsystem.theme.EchoDailyTheme
import com.jg.echodaily.core.presentation.desingsystem.theme.bgGradient
import com.jg.echodaily.echos.presentation.echos.components.EchoFilterRow
import com.jg.echodaily.echos.presentation.echos.components.EchoList
import com.jg.echodaily.echos.presentation.echos.components.EchosEmptyBackground
import com.jg.echodaily.echos.presentation.echos.components.EchosRecordedFloatingActionButton
import com.jg.echodaily.echos.presentation.echos.components.EchosTopBar

@Composable
fun EchosRoot(
    viewModel: EchosViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    EchosScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun EchosScreen(
    state: EchosState,
    onAction:(EchosAction) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            EchosRecordedFloatingActionButton(
                onClick = {
                    onAction(
                        EchosAction.OnFabClick
                    )
                }
            )
        },
        topBar = {
            EchosTopBar(
                onSettingsClick = {
                    onAction(EchosAction.OnSettingsClick)
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = MaterialTheme.colorScheme.bgGradient
                )
                .padding(innerPadding)
        ) {
            EchoFilterRow(
                moodChipContent = state.moodChipContent,
                hasActiveMoodFilters = state.hasActiveMoodFilters,
                selectedEchoFilterChip = state.selectedEchoFilterChip,
                moods = state.moods,
                topicChipTitle = state.topicChipTitle,
                hasActiveTopicFilters = state.hasActiveTopicFilters,
                topics = state.topics,
                onAction =onAction,
                modifier = Modifier
                    .fillMaxWidth()
            )
            when{
                state.isLoadingData ->{
                    CircularProgressIndicator(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .wrapContentSize(),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                !state.hasEchosRecorded -> {
                    EchosEmptyBackground(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    )
                }
                else ->{
                    EchoList(
                        sections =state.echoDaySection,
                        onPlayClick = {
                            onAction(EchosAction.OnPlayEchoClick(it))
                        },
                        onPauseClick = {
                            onAction(EchosAction.OnPauseClick)
                        },
                        onTrackSizeAvailable = {trackSize ->
                            onAction(EchosAction.OnTrackSizeAvailable(trackSize))
                        }
                    )
                }
            }
        }

    }

}

@Preview
@Composable
private fun EchosScreenPreview() {

    EchoDailyTheme {
        EchosScreen(
            state = EchosState(
                isLoadingData = false,
                hasEchosRecorded = false
            ),
            onAction = {}
        )
    }
}