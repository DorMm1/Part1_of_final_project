package DB_Repo;

import java.sql.*;


public class PostgreSQLConnection {

    private Connection connection = null;
    private Statement statement = null;

    public PostgreSQLConnection() {}

    public Connection getConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/weFlyDB",
                    "postgres", "********"
            );
            System.out.println("You have connected to the DB!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Statement getStatement(){
        try {
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

}
