package com.jg.echodaily.echos.presentation.echos.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jg.echodaily.core.presentation.desingsystem.theme.EchoDailyTheme
import com.jg.echodaily.core.presentation.util.UIText
import com.jg.echodaily.echos.presentation.echos.models.EchoDaySection
import com.jg.echodaily.echos.presentation.echos.models.RelativePosition
import com.jg.echodaily.echos.presentation.echos.models.TrackSizeInfo
import com.jg.echodaily.echos.presentation.preview.PreviewModel
import java.time.Instant
import java.time.ZonedDateTime

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EchoList(
    sections:List<EchoDaySection>,
    onPlayClick:(echoId:Int)->Unit,
    onPauseClick:()->Unit,
    onTrackSizeAvailable:(TrackSizeInfo)->Unit,
    modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    ) {
        sections.forEachIndexed { sectionIndex, (dateHeader, echos) ->
            stickyHeader {
                if(sectionIndex>0){
                    Spacer(modifier = Modifier.height(16.dp))
                }
                Text(
                    text = dateHeader.asString().uppercase(),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            itemsIndexed(items = echos,
                key ={_, echo -> echo.id
                }
            ){index, echo->
                EchoTimelineItem(
                    echoUi = echo,
                    relativePosition = when{
                        index == 0 && echos.size == 1 ->RelativePosition.SINGLE_ENTRY
                        index == 0 -> RelativePosition.FIRST
                        echos.lastIndex == index -> RelativePosition.LAST
                        else -> RelativePosition.IN_BETWEEN
                    },
                    onPlayClick = {onPlayClick(echo.id)},
                    onPauseClick = onPauseClick,
                    onTrackSizeAvailable = onTrackSizeAvailable
                )
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun EchoListPreview() {
    EchoDailyTheme {
        val today = remember {
            (1..3).map {
                PreviewModel.echoUi.copy(
                    id = it,
                    recordedAt = Instant.now()
                )
            }
        }
        val yesterday = remember {
            (4..6).map {
                PreviewModel.echoUi.copy(
                    id = it,
                    recordedAt = ZonedDateTime.now().minusDays(1).toInstant()
                )
            }
        }
        val from2DaysAgo = remember {
            (7..9).map {
                PreviewModel.echoUi.copy(
                    id = it,
                    recordedAt = ZonedDateTime.now().minusDays(2).toInstant()
                )
            }
        }

        val sections = remember {
            listOf(
                EchoDaySection(
                    dateHeader = UIText.Dynamic("Today"),
                    echos = today
                ),
                EchoDaySection(
                    dateHeader = UIText.Dynamic("Yesterday"),
                    echos = yesterday
                ),
                EchoDaySection(
                    dateHeader = UIText.Dynamic("2 days ago"),
                    echos = from2DaysAgo
                )
            )
        }
        EchoList(
            sections = sections,
            onPlayClick = {},
            onPauseClick = {},
            onTrackSizeAvailable = {}
        )
    }
}