package com.zulal.tourguide

import kotlinx.coroutines.flow.Flow

interface PlacesRepository{
    fun getAllPlacesStream(): Flow<List<Place>>//flow olarak döndüeür yerleri
    suspend fun insertPlace(place: Place)
    suspend fun deletePlace(place: Place)
}

class OfflinePlacesRepository(private val placeDao: PlaceDao) : PlacesRepository { //roomu yürütür
    override fun getAllPlacesStream(): Flow<List<Place>> {
        return placeDao.getAllPlaces()
    }

    override suspend fun insertPlace(place: Place) {
        placeDao.addPlace(place)
    }

    override suspend fun deletePlace(place: Place) {
        placeDao.deletePlace(place)
    }
}