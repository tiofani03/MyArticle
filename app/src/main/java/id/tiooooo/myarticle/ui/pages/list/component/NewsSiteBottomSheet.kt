package id.tiooooo.myarticle.ui.pages.list.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsSiteBottomSheet(
    sheetState: SheetState,
    currentSelections: List<String>,
    filterOptions: List<String>,
    onDismissRequest: () -> Unit,
    onApplyFilter: (List<String>) -> Unit
) {
    val selectedFilters = remember { mutableStateListOf(*currentSelections.toTypedArray()) }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Pilih Filter", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(16.dp))

            filterOptions.forEach { filter ->
                val isSelected = selectedFilters.contains(filter)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        modifier = Modifier,
                        checked = isSelected,
                        onCheckedChange = { checked ->
                            if (checked) {
                                selectedFilters.add(filter)
                            } else {
                                selectedFilters.remove(filter)
                            }
                        }
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        modifier = Modifier.weight(1f),
                        text = filter,
                        textAlign = TextAlign.Start
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    onApplyFilter(selectedFilters) // Terapkan filter
                    onDismissRequest()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Terapkan")
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

