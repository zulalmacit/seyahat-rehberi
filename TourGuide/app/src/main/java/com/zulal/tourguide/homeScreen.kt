package com.zulal.tourguide

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
    modifier: Modifier = Modifier //homescreen composable ui bileşeni bu parametreli alır
) {
    val places = mainViewModel.places.collectAsState(initial = listOf()).value //places adında bir değişken oluşturur ve mainViewModel'den yer listesini alır.
    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Tour Guide",
                style = MaterialTheme.typography.headlineMedium,//stil
                modifier = Modifier.align(Alignment.CenterHorizontally)//ortala
            )
            Spacer(modifier = Modifier.height(16.dp))//16dp yüksekliğinde boşluk

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(places) { place ->
                    PlaceCard(place, onDeleteClick = { mainViewModel.deletePlace(it) })
                }//place listesindeki her öge için placecard composibleni çağırır
            }
        }
        FloatingActionButton(
            onClick = { navController.navigate("APScreen") },// butona tıklandığında apscreen ile başka bir ekrana gider
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color.Black, // Background color
            contentColor = Color.White // Content color (icon or text)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Place") //butonun içinde yazıyor
        } //sağ alt köşedeki button
    }
}

@Composable
fun PlaceCard(place: Place, onDeleteClick: (Place) -> Unit) { //place bir nesne ondeleteclick place nesnesi alan fonk
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),//genişlik
        elevation = CardDefaults.cardElevation(4.dp)//yükseklik
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Place Name: ")
                    }
                    append(place.placeName)
                }, style = MaterialTheme.typography.bodyLarge)
                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Country: ")
                    }
                    append(place.country)
                }, style = MaterialTheme.typography.bodyLarge)
                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("City: ")
                    }
                    append(place.city)
                }, style = MaterialTheme.typography.bodyLarge)
                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("District: ")
                    }
                    append(place.districtName)
                }, style = MaterialTheme.typography.bodyLarge)
                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Construction Year: ")
                    }
                    append(place.constructionYear)
                }, style = MaterialTheme.typography.bodyLarge)
                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Ticket Price: ")
                    }
                    append(place.ticketPrice)
                }, style = MaterialTheme.typography.bodyLarge)
            }
            Button(//delete butonu eklenir satır sonuna
                onClick = { onDeleteClick(place) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black, // Button background color
                    contentColor = Color.White // Text color in the button
                )
            ) {
                Text("Delete")
            }
        }
    }
}
