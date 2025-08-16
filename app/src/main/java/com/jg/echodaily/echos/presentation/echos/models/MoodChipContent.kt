package com.jg.echodaily.echos.presentation.echos.models

import com.jg.echodaily.R
import com.jg.echodaily.core.presentation.util.UIText

data class MoodChipContent(
    val iconsRes:List<Int> = emptyList(),
    val title:UIText = UIText.StringResource(R.string.all_moods)
)