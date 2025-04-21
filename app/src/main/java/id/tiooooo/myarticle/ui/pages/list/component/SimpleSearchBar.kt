package id.tiooooo.myarticle.ui.pages.list.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import id.tiooooo.myarticle.data.implementation.local.entity.SearchEntity
import id.tiooooo.myarticle.ui.theme.EXTRA_SMALL_PADDING
import id.tiooooo.myarticle.ui.theme.MEDIUM_PADDING
import id.tiooooo.myarticle.ui.theme.ZERO_PADDING

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleSearchBar(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    textFieldState: TextFieldState,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
    history: List<SearchEntity>,
    onAddHistory: (String) -> Unit,
    onRemoveHistory: (SearchEntity) -> Unit
) {
    val padding = if (expanded) 0.dp else MEDIUM_PADDING

    Box(
        modifier
            .fillMaxWidth()
            .padding(top = 0.dp)
            .semantics { isTraversalGroup = true }
    ) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = padding, vertical = ZERO_PADDING)
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = 0f },
            inputField = {
                SearchBarDefaults.InputField(
                    query = textFieldState.text.toString(),
                    onQueryChange = {
                        textFieldState.edit { replace(0, length, it) }
                    },
                    onSearch = {
                        val query = textFieldState.text.toString()
                        onSearch(query)
                        onAddHistory(query)
                        onExpandedChange.invoke(false)
                    },
                    expanded = expanded,
                    onExpandedChange = { onExpandedChange.invoke(it) },
                    placeholder = {
                        Text(
                            modifier = Modifier.padding(start = EXTRA_SMALL_PADDING),
                            text = "Search"
                        )
                    },
                    leadingIcon = if (expanded) {
                        {
                            IconButton(
                                onClick = {
                                    onBack?.invoke()
                                    onExpandedChange(false)
                                }
                            ) {
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    } else null
                )
            },
            expanded = expanded,
            windowInsets = WindowInsets(top = 0.dp),
            onExpandedChange = { onExpandedChange.invoke(it) },
        ) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                if (history.isEmpty()) {
                    Text(
                        modifier = Modifier
                            .padding(MEDIUM_PADDING)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        text = "No history yet",
                        textAlign = TextAlign.Center,
                    )
                } else {
                    history.forEach { item ->
                        ListItem(
                            headlineContent = { Text(item.keyword) },
                            trailingContent = {
                                IconButton(onClick = { onRemoveHistory(item) }) {
                                    Icon(Icons.Default.Close, contentDescription = "Remove")
                                }
                            },
                            modifier = Modifier
                                .clickable {
                                    textFieldState.edit { replace(0, length, item.keyword) }
                                    onExpandedChange.invoke(false)
                                    onSearch.invoke(item.keyword)
                                }
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}