package com.jg.echodaily.echos.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jg.echodaily.R
import com.jg.echodaily.core.presentation.desingsystem.theme.EchoDailyTheme
import com.jg.echodaily.core.presentation.desingsystem.theme.Pause
import com.jg.echodaily.core.presentation.util.defaultShadow
import com.jg.echodaily.echos.presentation.echos.models.PlaybackState
import com.jg.echodaily.echos.presentation.models.MoodUI

@Composable
fun EchoPlaybackButton(
    playbackState: PlaybackState,
    onPlayClick: ()->Unit,
    onPausedClick:()->Unit,
    colors:IconButtonColors,
    modifier: Modifier = Modifier
) {
    FilledIconButton(
        onClick = when(playbackState){
            PlaybackState.PLAYING -> onPausedClick
            PlaybackState.PAUSED,
            PlaybackState.STOPPED -> onPlayClick
        },
        colors = colors,
        modifier = modifier.defaultShadow()
    ) {
        Icon(
            imageVector = when(playbackState){
                PlaybackState.PLAYING -> Icons.Filled.Pause
                PlaybackState.PAUSED,
                PlaybackState.STOPPED -> Icons.Filled.PlayArrow
            },
            contentDescription = when(playbackState){
                PlaybackState.PLAYING -> stringResource(R.string.playing)
                PlaybackState.PAUSED -> stringResource(R.string.pause)
                PlaybackState.STOPPED -> stringResource(R.string.stopped)
            }
        )
    }
}

@Preview
@Composable
private fun EchoPlaybackButtonPreview() {
    EchoDailyTheme {
        EchoPlaybackButton(
            playbackState = PlaybackState.PLAYING,
            onPausedClick = {},
            onPlayClick = {},
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MoodUI.SAD.colorSet.vivid
            )

        )
    }
}