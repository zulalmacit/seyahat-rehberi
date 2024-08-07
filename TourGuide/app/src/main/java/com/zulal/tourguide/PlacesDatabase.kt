package com.zulal.tourguide

import androidx.room.Database
import androidx.room.RoomDatabase//veritabanı yönetim işlemleri

@Database(entities = [Place::class], version = 1)//databasede bulunan tabloların listesini belirtir
abstract class PlacesDatabase: RoomDatabase() {
    abstract fun placeDao(): PlaceDao
}// MainActivity veya başka bir sınıf bu veritabanı sınıfını kullanarak veritabanı işlemlerini yapabilir.






