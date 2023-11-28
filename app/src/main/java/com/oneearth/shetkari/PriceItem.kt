package com.oneearth.shetkari

data class PriceItem(
    val state: String,
    val district: String,
    val market: String,
    val commodity: String,
    val variety: String,
    val grade: String,
    val arrivalDate: String,
    val minPrice: Double,
    val maxPrice: Double,
    val modalPrice: Double
)


