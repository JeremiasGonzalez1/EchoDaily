package com.jg.echodaily.echos.presentation.echos

import com.jg.echodaily.echos.presentation.echos.models.EchoFilterChip

sealed interface EchosAction {
    data object OnMoodChipClick: EchosAction
    data object OnTopicChickClick : EchosAction
    data object OnFabClick: EchosAction
    data object OnFabLongClick: EchosAction
    data object OnSettingsClick:EchosAction
    data class OnRemoveFilters( val filterType:EchoFilterChip): EchosAction
}