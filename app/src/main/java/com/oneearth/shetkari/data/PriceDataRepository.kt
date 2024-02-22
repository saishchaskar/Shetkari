package com.oneearth.shetkari.data

import java.sql.Connection
import java.sql.SQLException

// PriceDataRepository.kt
class PriceDataRepository {

    fun getAllPriceData(): List<PriceData> {
        var connection: Connection? = null
        val priceDataList = mutableListOf<PriceData>()

        try {
            connection = DatabaseConnection.getConnection()

            // Execute your SQL query to fetch data
            val query = "SELECT * FROM crop_prices"
            val statement = connection?.createStatement()
            val resultSet = statement?.executeQuery(query)

            // Map the result set to PriceData objects
            while (resultSet?.next() == true) {
                val priceData = PriceData(
                    resultSet.getInt("id"),
                    resultSet.getDouble("modal_price"),
                    resultSet.getDouble("min_price"),
                    resultSet.getDouble("max_price"),
                    resultSet.getString("district_name"),
                    resultSet.getString("market_name"),
                    resultSet.getString("commodity"),
                    resultSet.getString("price_date")
                )
                priceDataList.add(priceData)
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        } finally {
            DatabaseConnection.closeConnection(connection)
        }

        return priceDataList
    }
}


