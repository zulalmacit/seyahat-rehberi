package com.zulal.tourguide
import androidx.room.Entity//room veritabanının tablosu olarak işaretlemek için
import androidx.room.PrimaryKey

@Entity(tableName = "Places")
data class Place(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,//başlangıç değeri sıfır oto artar. birincil anahtar
    val placeName: String,
    val country: String,
    val city: String,
    val districtName: String,
    val constructionYear: String,
    val ticketPrice: String
)