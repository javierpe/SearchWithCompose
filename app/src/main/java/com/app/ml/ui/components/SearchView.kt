@file:OptIn(ExperimentalMaterial3Api::class)

package com.app.ml.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.ml.ui.theme.SecondaryLight
import com.app.ml.ui.theme.Typography

const val ALPHA = 0.5f

@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    onValueChanged: (String) -> Unit,
    onSearch: (String) -> Unit
) {

    val valueState = remember { mutableStateOf("") }

    OutlinedTextField(
        value = valueState.value,
        onValueChange = {
            valueState.value = it
            onValueChanged.invoke(it)
        },
        modifier = modifier
            .wrapContentHeight(unbounded = true)
            .height(52.dp)
            .testTag("search_input"),
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colorScheme.primary,
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.primary.copy(alpha = ALPHA),
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        shape = RoundedCornerShape(16.dp),
        placeholder = {
            placeholder?.let {
                Text(
                    text = it,
                    style = Typography.labelMedium,
                    modifier = Modifier
                        .wrapContentSize(unbounded = true)
                        .testTag("search_placeholder"),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = ALPHA)
                )
            }
        },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                modifier = Modifier.testTag("search_icon")
            )
        },
        textStyle = Typography.labelMedium,
        keyboardActions = KeyboardActions(
            onDone = {
                onSearch.invoke(valueState.value)
            }
        )
    )
}

@Composable
@Preview
fun PreviewSearchView() {
    SearchView(
        placeholder = "Buscar",
        onValueChanged = { }
    ) { }
}