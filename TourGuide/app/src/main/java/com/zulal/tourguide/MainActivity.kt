package com.zulal.tourguide

import androidx.compose.foundation.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.zulal.tourguide.ui.theme.TourGuideTheme

import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(applicationContext, PlacesDatabase::class.java, "place-db").build()
        //room kullanarak bir veritabanı oluşturur. placedatabasenin öreneğini db e atar
        val mainViewModel = MainViewModel(OfflinePlacesRepository(db.placeDao()), ioDispatcher = Dispatchers.IO)
        //MainViewModel adlı bir ViewModel oluşturur ve ona bir OfflinePlacesRepository örneği ve ioDispatcher olarak Dispatchers.IO geçirir. db.placeDao() ile veritabanı üzerinde DAO (Data Access Object) elde edilir
        setContent {
            TourGuideTheme{
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.turizm),
                        contentDescription = null, // Ekran okuyucular için içerik açıklaması
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds // Resmi boyutlandırma ölçeği
                    )
                    val navController = rememberNavController()
                    PlacesNavHost(mainViewModel, navController)//placenavhost çağrılır kontrolu mainview ve navcontroller ile sağlanır
                }
            }
        }

    }
}