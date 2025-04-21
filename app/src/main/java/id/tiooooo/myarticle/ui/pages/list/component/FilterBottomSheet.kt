package id.tiooooo.myarticle.ui.pages.list.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import id.tiooooo.myarticle.ui.theme.EXTRA_LARGE_PADDING
import id.tiooooo.myarticle.ui.theme.EXTRA_SMALL_PADDING
import id.tiooooo.myarticle.ui.theme.HUGE_PADDING
import id.tiooooo.myarticle.ui.theme.MEDIUM_PADDING
import id.tiooooo.myarticle.ui.theme.SMALL_PADDING
import id.tiooooo.myarticle.ui.theme.textMedium12
import id.tiooooo.myarticle.utils.SortValue
import id.tiooooo.myarticle.utils.getLabel
import id.tiooooo.myarticle.utils.getSlug

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FilterBottomSheet(
    sheetState: SheetState,
    currentSort: String,
    sortOptions: List<SortValue>,
    currentNewsSite: List<String>,
    newsSiteOptions: List<String>,
    onDismissRequest: () -> Unit,
    onSortSelected: (String) -> Unit,
    onNewsSiteSelected: (List<String>) -> Unit
) {
    val defaultSortSlug = sortOptions.first().getSlug()
    var selectedSort by remember { mutableStateOf(currentSort) }
    val selectedNewsSites = remember { mutableStateListOf(*currentNewsSite.toTypedArray()) }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(EXTRA_LARGE_PADDING)
                .verticalScroll(rememberScrollState())
        ) {
            Row {
                Text("Urutkan Berdasarkan", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.weight(1f))
                Text(
                    text = "Reset",
                    color = Color.Red,
                    modifier = Modifier
                        .clickable {
                            selectedSort = defaultSortSlug
                            selectedNewsSites.clear()
                        }
                        .padding(horizontal = SMALL_PADDING, vertical = EXTRA_SMALL_PADDING),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                )
            }
            Spacer(Modifier.height(MEDIUM_PADDING))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING),
                verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
            ) {
                sortOptions.forEach { sort ->
                    val slug = sort.getSlug()
                    FilterChip(
                        selected = selectedSort == slug,
                        onClick = { selectedSort = slug },
                        label = {
                            Text(
                                text = sort.getLabel(),
                                style = textMedium12()
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }
            }

            Spacer(Modifier.height(HUGE_PADDING))


            Text("Pilih News Site", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(MEDIUM_PADDING))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING),
                verticalArrangement = Arrangement.spacedBy(EXTRA_SMALL_PADDING)
            ) {
                newsSiteOptions.forEach { site ->
                    val isSelected = selectedNewsSites.contains(site)
                    FilterChip(
                        selected = isSelected,
                        onClick = {
                            if (isSelected) selectedNewsSites.remove(site)
                            else selectedNewsSites.add(site)
                        },
                        label = {
                            Text(
                                text = site,
                                style = textMedium12()
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }
            }

            Spacer(Modifier.height(HUGE_PADDING))
            Button(
                onClick = {
                    onSortSelected(selectedSort)
                    onNewsSiteSelected(selectedNewsSites)
                    onDismissRequest()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Terapkan")
            }
            Spacer(Modifier.height(MEDIUM_PADDING))
        }
    }
}
