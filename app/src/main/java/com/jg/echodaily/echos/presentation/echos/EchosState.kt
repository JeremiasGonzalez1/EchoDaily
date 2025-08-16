package com.jg.echodaily.echos.presentation.echos

import android.adservices.topics.Topic
import com.jg.echodaily.R
import com.jg.echodaily.core.presentation.desingsystem.dropdowns.Selectable
import com.jg.echodaily.core.presentation.desingsystem.dropdowns.Selectable.Companion.asUnselectedItems
import com.jg.echodaily.core.presentation.util.UIText
import com.jg.echodaily.echos.presentation.echos.models.EchoFilterChip
import com.jg.echodaily.echos.presentation.echos.models.MoodChipContent
import com.jg.echodaily.echos.presentation.models.MoodUI

data class EchosState(
        val hasEchosRecorded :Boolean = false,
        val hasActiveTopicFilters:Boolean = false,
        val hasActiveMoodFilters:Boolean = false,
        val isLoadingData: Boolean = false,
        val moods:List<Selectable<MoodUI>> = emptyList(),
        val topics : List<Selectable<String>> = listOf("Love", "Happy", "Work").asUnselectedItems(),
        val moodChipContent : MoodChipContent = MoodChipContent(),
        val selectedEchoFilterChip: EchoFilterChip? = null,
        val topicChipTitle: UIText = UIText.StringResource(R.string.all_topics)
)