package com.jg.echodaily.echos.presentation.echos

import com.jg.echodaily.echos.presentation.echos.models.EchoFilterChip
import com.jg.echodaily.echos.presentation.echos.models.TrackSizeInfo
import com.jg.echodaily.echos.presentation.models.MoodUI

sealed interface EchosAction {
    data object OnMoodChipClick: EchosAction
    data object OnDismissMoodDropDown: EchosAction
    data class OnFilterByMoodClick(val mood: MoodUI): EchosAction
    data object OnTopicChipClick : EchosAction
    data object OnDismissTopicDropDown: EchosAction
    data class OnFilterByTopicClick(val topic: String): EchosAction
    data object OnFabClick: EchosAction
    data object OnFabLongClick: EchosAction
    data object OnSettingsClick:EchosAction
    data class OnRemoveFilters( val filterType:EchoFilterChip): EchosAction
    data class OnPlayEchoClick(val echoId:Int):EchosAction
    data object OnPauseClick:EchosAction
    data class OnTrackSizeAvailable(val trackSize: TrackSizeInfo):EchosAction
}