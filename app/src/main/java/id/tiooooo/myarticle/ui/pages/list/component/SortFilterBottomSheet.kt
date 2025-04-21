package id.tiooooo.myarticle.ui.pages.list.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import id.tiooooo.myarticle.ui.theme.EXTRA_LARGE_PADDING
import id.tiooooo.myarticle.ui.theme.MEDIUM_PADDING
import id.tiooooo.myarticle.ui.theme.SMALL_PADDING
import id.tiooooo.myarticle.utils.SortValue
import id.tiooooo.myarticle.utils.getLabel
import id.tiooooo.myarticle.utils.getSlug

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortFilterBottomSheet(
    sheetState: SheetState,
    currentSort: String,
    sortOptions: List<SortValue>,
    onDismissRequest: () -> Unit,
    onSortSelected: (String) -> Unit
) {
    var selectedSort by rememberSaveable { mutableStateOf(currentSort) }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text("Urutkan Berdasarkan", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(16.dp))

            sortOptions.forEach { sort ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = selectedSort == sort.getSlug(),
                            onClick = { selectedSort = sort.getSlug() }
                        )
                        .padding(vertical = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedSort == sort.getSlug(),
                        onClick = { selectedSort = sort.getSlug() }
                    )
                    Spacer(modifier = Modifier.width(SMALL_PADDING))
                    Text(sort.getLabel())
                }
            }

            Spacer(Modifier.height(EXTRA_LARGE_PADDING))

            Button(
                onClick = {
                    onSortSelected(selectedSort)
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
