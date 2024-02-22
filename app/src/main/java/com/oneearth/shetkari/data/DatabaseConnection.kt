package com.oneearth.shetkari.data

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object DatabaseConnection {

    private const val DATABASE_URL = "jdbc:postgresql://localhost:5432/crop_price_database"
    private const val DATABASE_USER = "postgres"
    private const val DATABASE_PASSWORD = "Saish@2526"

    fun getConnection(): Connection? {
        return try {
            Class.forName("com.impossibl.postgres.jdbc.PGDriver")
            DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)
        } catch (e: SQLException) {
            e.printStackTrace()
            null
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            null
        }
    }

    fun closeConnection(connection: Connection?) {
        try {
            connection?.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}
