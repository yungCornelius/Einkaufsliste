import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable

@Composable
fun EinkaufsApp() {
    var artikel by remember { mutableStateOf(TextFieldValue("")) }
    var artikelListe by remember { mutableStateOf(listOf<String>()) }

    Column(modifier = Modifier.padding(16.dp)) {
        // Eingabefeld
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            BasicTextField(
                value = artikel,
                onValueChange = { artikel = it },
                modifier = Modifier.weight(1f).padding(end = 8.dp),
                decorationBox = { innerTextField ->
                    Box(Modifier.padding(8.dp)) {
                        if (artikel.text.isEmpty()) Text("Artikel eingeben...")
                        innerTextField()
                    }
                }
            )
            Button(onClick = {
                if (artikel.text.isNotEmpty()) {
                    artikelListe = artikelListe + artikel.text
                    artikel = TextFieldValue("")
                }
            }) {
                Text("Hinzufügen")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Liste der Artikel
        LazyColumn {
            items(artikelListe) { item ->
                ListItem(
                    headlineContent = { Text(item) }, // Text-Parameter wird hier als Composable übergeben
                    modifier = Modifier.clickable { artikelListe = artikelListe - item }
                )
            }
        }
    }
}