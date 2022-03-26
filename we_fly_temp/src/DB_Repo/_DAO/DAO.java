package DB_Repo._DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface DAO<T>{
    T get(long id);
    List getAll();
    void add(T t);
    void remove(T t);
    void update(T t,long id);
    default void closeQuietly(Connection connection, Statement statement){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) { /* Ignored */}
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) { /* Ignored */}
        }
    }

}
