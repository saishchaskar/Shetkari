package com.oneearth.shetkari.data

import androidx.room.Entity

@Entity(tableName = "crop_prices")
data class PriceData(
    val id: Int,
    val modalPrice: Double,
    val minPrice: Double,
    val maxPrice: Double,
    val districtName: String,
    val marketName: String,
    val commodity: String,
    val priceDate: String
)
