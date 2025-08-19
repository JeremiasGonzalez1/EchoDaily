package com.jg.echodaily.echos.presentation.models

import com.jg.echodaily.echos.presentation.echos.models.PlaybackState
import com.jg.echodaily.echos.presentation.util.toReadableTime
import java.time.Instant as JavaInstant
import kotlin.time.Duration

data class EchoUi(
    val id:Int,
    val title:String,
    val mood: MoodUI,
    val recordedAt: JavaInstant,
    val note:String?,
    val topics:List<String>,
    val amplitudes:List<Float>,
    val playbackTotalDuration:Duration,
    val playbackCurrentDuration:Duration = Duration.ZERO,
    val playbackState:PlaybackState = PlaybackState.STOPPED
){
    val formattedRecordedAt = recordedAt.toReadableTime()
    val playbackRatio = (playbackCurrentDuration / playbackTotalDuration).toFloat()
}
