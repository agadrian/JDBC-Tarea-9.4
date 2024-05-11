package db_connection


import org.apache.commons.dbcp2.BasicDataSource
import javax.sql.DataSource


object DataSourceFactory {

    /**
     * Enumeración que representa los tipos de DataSource disponibles.
     */
    enum class DataSourceType {
        HIKARI,
        JDBC,
        XML,
        JSON
    }


    /**
     * Obtiene una instancia de DataSource según el tipo especificado.
     *
     * @param dataSourceType El tipo de DataSource deseado.
     * @return Una instancia de DataSource configurada según el tipo especificado.
     */
    fun getDS(dataSourceType: DataSourceType): DataSource {
        return when (dataSourceType) {
            DataSourceType.JDBC -> {
                val dataSource = BasicDataSource()
                dataSource.url = "jdbc:h2:./default"
                dataSource.username = "user"
                dataSource.password = "user"
                dataSource.driverClassName = "org.h2.Driver"
                dataSource.maxTotal = 10 // máximo número de conexiones en el pool
                dataSource.defaultAutoCommit = true
                dataSource.defaultTransactionIsolation = java.sql.Connection.TRANSACTION_REPEATABLE_READ
                dataSource
            }


            DataSourceType.XML -> TODO()

            DataSourceType.HIKARI -> TODO()

            DataSourceType.JSON -> TODO()
        }
    }
}
