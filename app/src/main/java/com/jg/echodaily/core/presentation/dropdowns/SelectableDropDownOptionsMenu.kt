package com.jg.echodaily.core.presentation.dropdowns

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.jg.echodaily.R
import com.jg.echodaily.core.presentation.dropdowns.Selectable.Companion.asUnselectedItems
import com.jg.echodaily.core.presentation.theme.EchoDailyTheme

@Composable
fun<T> SelectableDropDownOptionsMenu(
    items:List<Selectable<T>>,
    onDismiss:()->Unit,
    onItemClick:(Selectable<T>) ->Unit,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    dropDownOffset:IntOffset  = IntOffset.Zero,
    maxDropDownHeight : Dp = Dp.Unspecified,
    dropDownExtras: SelectableOptionExtra? = null
) {
    Popup(
        onDismissRequest =  onDismiss,
        offset = dropDownOffset
    ) {
        Surface(
            color =MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(10.dp),
            shadowElevation = 4.dp,
            modifier = modifier
                .heightIn(max = maxDropDownHeight)
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
        ) {
            LazyColumn(

            ) {

            }
        }
    }
}

@Preview
@Composable
private fun SelectableDropDownOptionsMenuPreview() {
    EchoDailyTheme {
        SelectableDropDownOptionsMenu(
            items = (1 .. 5).map {
                "Hello world $it"
            }.asUnselectedItems(),
            onDismiss = {},
            onItemClick = {},
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.hashtag),
                    contentDescription = null
                )
            },
            maxDropDownHeight = 500.dp,
            dropDownExtras = SelectableOptionExtra(
                text = "all topics",
                onClick = {}
            )

        )
    }
}