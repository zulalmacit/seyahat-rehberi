package com.zulal.tourguide
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun PlacesNavHost(
    mainViewModel: MainViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = "APScreen", modifier = modifier) {//başlangıç sayfası apscreen
        composable(route = "HS") {
            HomeScreen(mainViewModel, navController, Modifier.fillMaxSize())
        }

        composable(route = "APScreen") {
            APScreen(onAddButtonClick = { place ->
                mainViewModel.addPlace(place)
                navController.navigate("HS")// yeni bir yer eklendiyse homescreene git
            }, navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun APScreen(onAddButtonClick: (Place) -> Unit = {}, navController: NavController? = null) {//yeni bir yer eklemek için button kullan
    var placeName by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var districtName by remember { mutableStateOf("") }
    var constructionYear by remember { mutableStateOf("") }
    var ticketPrice by remember { mutableStateOf("") }
//form girişlerini tutmak için
    val focusManager = LocalFocusManager.current//klavye odaklaması

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
        text = "Add New Place",
        style = MaterialTheme.typography.headlineMedium.copy(//metni ortalama
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White // Metin rengini beyaz yapmak için
        ),
        modifier = Modifier.padding(bottom = 16.dp)
    )

        TextField(
            value = placeName,
            onValueChange = { placeName = it },
            label = { Text("Place Name") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = country,
            onValueChange = { country = it },
            label = { Text("Country") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("City") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = districtName,
            onValueChange = { districtName = it },
            label = { Text("Districh Name") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = constructionYear,
            onValueChange = { constructionYear = it },
            label = { Text("Construction Year") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = ticketPrice,
            onValueChange = { ticketPrice = it },
            label = { Text("Ticket Price") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })

        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(//butonları yanyana yerleştirmek için
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    onAddButtonClick(Place(
                        placeName = placeName,
                        country = country,
                        city = city,
                        districtName = districtName,
                        constructionYear =constructionYear,
                        ticketPrice=ticketPrice

                    ))
                    navController?.navigate("HS")
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black, // Butonun arka plan rengi
                    contentColor = Color.White // Butonun içindeki metin rengi
                )
            ) {
                Text("Add Place") // yeni bir place oluşturulur ardından nav kontroller ile hs ekranına gidilir
            }
            Spacer(modifier = Modifier.width(8.dp))


            Button(

                onClick = { navController?.navigate("HS") },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black, // Butonun arka plan rengi
                    contentColor = Color.White // Butonun içindeki metin rengi
                )
            ) {
                Text("Browse")
            }
        }
    }
}