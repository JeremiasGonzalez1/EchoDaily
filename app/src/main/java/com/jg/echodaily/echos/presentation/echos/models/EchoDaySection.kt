package com.jg.echodaily.echos.presentation.echos.models

import com.jg.echodaily.core.presentation.util.UIText
import com.jg.echodaily.echos.presentation.models.EchoUi

data class EchoDaySection(
    val dateHeader:UIText,
    val echos:List<EchoUi>
)
