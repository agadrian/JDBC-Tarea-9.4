package dao

import output.IConsole
import java.sql.SQLException
import java.util.*
import javax.sql.DataSource

class UserDAOH2(
    private val dataSource: DataSource,
    private val console: IConsole
) : IUserDAO {

    // User entity nulable, por si le llega uno invalido, devolver nulo
    override fun create(user: UserEntity): UserEntity? {
        val sql = "INSERT INTO tuser (id, name, email) VALUES (?, ?, ?)"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, user.id.toString())
                    stmt.setString(2, user.name)
                    stmt.setString(3, user.email)

                    // Devuleve el numero de registros afectados
                    val rs = stmt.executeUpdate()

                    // Comprobacion de que devuleva o no alguna fila afectada
                    if (rs == 1){
                        user
                    }else{
                        console.showMessage("ERROR - Insert query failed ($rs rows affected) ")
                        null
                    }

                }
            }
        }catch (e: SQLException){
            console.showMessage("ERROR - Insert query failed (${e.message})")
            null
        }
    }

    override fun getById(id: UUID): UserEntity? {
        val sql = "SELECT * FROM tuser WHERE id = ?"
        return try{
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id.toString())
                    val rs = stmt.executeQuery()
                    if (rs.next()) {
                        UserEntity(
                            id = UUID.fromString(rs.getString("id")),
                            name = rs.getString("name"),
                            email = rs.getString("email")
                        )
                    } else {
                        null
                    }
                }
            }
        }catch (e: SQLException){
            console.showMessage("ERROR - Insert query failed (${e.message})")
            null
        }
    }

    override fun getAll(): List<UserEntity>? {
        val sql = "SELECT * FROM tuser"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    val rs = stmt.executeQuery()
                    val users = mutableListOf<UserEntity>()
                    while (rs.next()) {
                        users.add(
                            UserEntity(
                                id = UUID.fromString(rs.getString("id")),
                                name = rs.getString("name"),
                                email = rs.getString("email")
                            )
                        )
                    }
                    users
                }
            }
        }catch (e: SQLException){
            console.showMessage("ERROR - Get all failed (${e.message})")
            null
        }
    }

    override fun update(user: UserEntity): UserEntity? {
        val sql = "UPDATE tuser SET name = ?, email = ? WHERE id = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, user.name)
                    stmt.setString(2, user.email)
                    stmt.setString(3, user.id.toString())
                    stmt.executeUpdate()
                    user
                }
            }
        }catch (e:SQLException){
            console.showMessage("ERROR - Update query failed (${e.message})")
            null
        }
    }

    override fun delete(id: UUID): Boolean {
        val sql = "DELETE FROM tuser WHERE id = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id.toString())
                    stmt.executeUpdate() == 1
                }

            }
        }catch (e: SQLException){
            console.showMessage("ERROR - Delete query failed (${e.message})")
            false
        }
    }
}