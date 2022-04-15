/*
 * Copyright 2021 Paulo Pereira
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.presentation.searchbarsample

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusEventModifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.presentation.searchbarcomponents.autocomplete.AutoCompleteBox
import com.example.androiddevchallenge.presentation.searchbarcomponents.autocomplete.utils.AutoCompleteSearchBarTag
import com.example.androiddevchallenge.presentation.searchbarcomponents.autocomplete.utils.asAutoCompleteEntities
import com.example.androiddevchallenge.presentation.searchbarcomponents.searchbar.TextSearchBar
import com.revature.popquiz.model.dataobjects.SearchWidgetState
import com.revature.popquiz.viewmodels.SearchBarViewModel
import java.util.Locale


@ExperimentalAnimationApi
@Composable
fun AutoCompleteValueSample(items: List<String>, searchBarViewModel: SearchBarViewModel)
{
    // Search Bar View Model
    val searchWidgetState by searchBarViewModel.searchWidgetState
    val searchTextState by searchBarViewModel.searchTextState

    val items = listOf(
        "Java",
        "Kotlin",
        "Databases",
        "Jetpack Compose",
        "REST API's"
    )
    val autoCompleteEntities = items.asAutoCompleteEntities(
        filter = { item, query ->
            item.toLowerCase(Locale.getDefault())
                .startsWith(query.toLowerCase(Locale.getDefault()))
        }
    )

    AutoCompleteBox(
        items = autoCompleteEntities,
        itemContent = { item ->
            ValueAutoCompleteItem(item.value)
        }
    )
    {
        var value by remember { mutableStateOf("") }
        val view = LocalView.current

        onItemSelected()
        { item ->
            value = item.value
            filter(value)
            view.clearFocus()
        }

        TextSearchBar(
            modifier = Modifier.testTag(AutoCompleteSearchBarTag),
            value = value,
            label = "Search Quizzes",
            onDoneActionClick =
            {
                view.clearFocus()
            },
            onClearClick =
            {
                value = ""
                filter(value)
                view.clearFocus()
            },
            onFocusChanged =
            {
                    focusState -> isSearching = focusState.hasFocus
            },
            onValueChanged = { query ->
                value = query
                filter(value)
            }
        )
    }
}

@Composable
fun ValueAutoCompleteItem(item: String)
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        Text(text = item, style = MaterialTheme.typography.subtitle2)
    }
}
