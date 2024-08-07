package com.zulal.tourguide
import androidx.room.Dao //Android Room kütüphanesi kullanılarak bir DAO (Data Access Object) arayüzü tanımlar.
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query// roomda sql yazmak için
import kotlinx.coroutines.flow.Flow// veri akışı sağlamak için

@Dao
interface PlaceDao {
    @Query("SELECT * from places ORDER BY placeName ASC")//place tablosundaki tüm verileri placeNAme sütununa gÖre artan sıralar
    fun getAllPlaces(): Flow<List<Place>>

    @Insert
    fun addPlace(place: Place)

    @Delete
    fun deletePlace(place: Place)
}