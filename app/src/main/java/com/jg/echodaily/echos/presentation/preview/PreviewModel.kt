package com.jg.echodaily.echos.presentation.preview

import com.jg.echodaily.echos.presentation.echos.models.PlaybackState
import com.jg.echodaily.echos.presentation.models.EchoUi
import com.jg.echodaily.echos.presentation.models.MoodUI
import java.time.Instant
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

data object PreviewModel{
    val echoUi = EchoUi(
        id = 1,
        title = "My audio memo",
        mood = MoodUI.STRESSED,
        recordedAt = Instant.now(),
        note = buildString {
            repeat(100){
                append("Hello ")
            }
        },
        topics = listOf("Love", "Work"),
        amplitudes = (1..50).map { Random.nextFloat() },
        playbackTotalDuration = 250.seconds,
        playbackCurrentDuration = 120.seconds,
        playbackState = PlaybackState.PAUSED
    )
}