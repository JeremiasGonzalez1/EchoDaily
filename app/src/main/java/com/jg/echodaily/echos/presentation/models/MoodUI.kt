package com.jg.echodaily.echos.presentation.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.jg.echodaily.R
import com.jg.echodaily.core.presentation.desingsystem.theme.Excited25
import com.jg.echodaily.core.presentation.desingsystem.theme.Excited35
import com.jg.echodaily.core.presentation.desingsystem.theme.Excited80
import com.jg.echodaily.core.presentation.desingsystem.theme.Neutral25
import com.jg.echodaily.core.presentation.desingsystem.theme.Neutral35
import com.jg.echodaily.core.presentation.desingsystem.theme.Neutral80
import com.jg.echodaily.core.presentation.desingsystem.theme.Peaceful25
import com.jg.echodaily.core.presentation.desingsystem.theme.Peaceful35
import com.jg.echodaily.core.presentation.desingsystem.theme.Peaceful80
import com.jg.echodaily.core.presentation.desingsystem.theme.Sad25
import com.jg.echodaily.core.presentation.desingsystem.theme.Sad35
import com.jg.echodaily.core.presentation.desingsystem.theme.Sad80
import com.jg.echodaily.core.presentation.desingsystem.theme.Stressed25
import com.jg.echodaily.core.presentation.desingsystem.theme.Stressed35
import com.jg.echodaily.core.presentation.desingsystem.theme.Stressed80
import com.jg.echodaily.core.presentation.util.UIText

enum class MoodUI(
    val iconSet: MoodIconSet,
    val colorSet: MoodColorSet,
    val title:UIText
){
    STRESSED(
        iconSet = MoodIconSet(
            fill = R.drawable.emoji_stressed,
            outline =R.drawable.emoji_stressed_outline
        ),
        colorSet = MoodColorSet(
            vivid = Stressed80,
            desaturated = Stressed35,
            faded = Stressed25
        ),
        title = UIText.StringResource(R.string.stressed)
    ),
    SAD(
        iconSet = MoodIconSet(
            fill = R.drawable.emoji_sad,
            outline =R.drawable.emoji_sad_outline
        ),
        colorSet = MoodColorSet(
            vivid = Sad80,
            desaturated = Sad35,
            faded = Sad25
        ),
        title = UIText.StringResource(R.string.sad)
    ),
    NEUTRAL(
        iconSet = MoodIconSet(
            fill = R.drawable.emoji_neutral,
            outline =R.drawable.emoji_neutral_outline
        ),
        colorSet = MoodColorSet(
            vivid = Neutral80,
            desaturated = Neutral35,
            faded = Neutral25
        ),
        title = UIText.StringResource(R.string.neutral)
    ),
    PEACEFUL(
        iconSet = MoodIconSet(
            fill = R.drawable.emoji_peaceful,
            outline =R.drawable.emoji_peaceful_outline
        ),
        colorSet = MoodColorSet(
            vivid = Peaceful80,
            desaturated = Peaceful35,
            faded = Peaceful25
        ),
        title = UIText.StringResource(R.string.peaceful)
    ),
    EXCITED(
        iconSet = MoodIconSet(
            fill = R.drawable.emoji_excited,
            outline =R.drawable.emoji_excited_outline
        ),
        colorSet = MoodColorSet(
            vivid = Excited80,
            desaturated = Excited35,
            faded = Excited25
        ),
        title = UIText.StringResource(R.string.excited)
    )
}

data class MoodIconSet(
    @DrawableRes val fill:Int,
    @DrawableRes val outline: Int
)

data class MoodColorSet(
    val vivid:Color,
    val desaturated:Color,
    val faded: Color
)