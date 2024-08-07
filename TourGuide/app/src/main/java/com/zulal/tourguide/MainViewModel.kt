package com.zulal.tourguide
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainViewModel( //veri katmanı ile UI arasında aracılık yapar
    private val repository: PlacesRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    val places = repository.getAllPlacesStream()//yerlerin akışını stream elde eder. ui verileri

    fun addPlace(place: Place) = viewModelScope.launch(ioDispatcher) {
        repository.insertPlace(place)//veritabanına yeni yerler ekler
    }

    fun deletePlace(place: Place) = viewModelScope.launch(ioDispatcher) {
        repository.deletePlace(place)//veritabanındaki bir yeri siler
    }
}