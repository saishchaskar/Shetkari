package com.oneearth.shetkari.data

import kotlinx.serialization.Serializable

@Serializable
data class Record(
    val state: String,
    val district: String,
    val market: String,
    val commodity: String,
    val min_price: String, // Make sure these properties match the JSON/XML response
    val max_price: String
)

