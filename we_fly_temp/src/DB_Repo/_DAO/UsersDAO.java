package DB_Repo._DAO;

import DB_Repo.PostgreSQLConnection;
import DB_Repo._POCO.UserPOCO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO implements DAO<UserPOCO> {

    List<UserPOCO> usersPOCOList = new ArrayList<>();
    PostgreSQLConnection postgreSQLConnection = new PostgreSQLConnection();
    Connection connection = postgreSQLConnection.getConnection();
    Statement stm = postgreSQLConnection.getStatement();


    @Override
    public UserPOCO get(long id) {
        UserPOCO user = null;
        try {
            var result = stm.executeQuery("SELECT * FROM users WHERE id =" + id);
            while (result.next()) {
                user =
                        new UserPOCO(result.getLong("id"),
                                result.getString("username"),
                                result.getString("userpassword"),
                                result.getString("email"),
                                result.getInt("user_role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List getAll() {
        try {
            var result = stm.executeQuery("SELECT * FROM users");
            while (result.next()) {
                usersPOCOList.add(
                        new UserPOCO(result.getLong("id"),
                                result.getString("username"),
                                result.getString("userpassword"),
                                result.getString("email"),
                                result.getInt("user_role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersPOCOList;
    }

    @Override
    public void add(UserPOCO userPOCO) {
        int result = 0;
        try {
            result = stm.executeUpdate("INSERT INTO users (username,userpassword,email,user_role) " +
                    "VALUES" +
                    "('" + userPOCO.username + "','" + userPOCO.userpassword + "','" + userPOCO.email + "'," + userPOCO.user_role + ")");
        } catch (SQLException e) {
            System.out.println("please be more aware !!!!!!!!");
        }
        if (result != 0)
            System.out.println("User added successfully");
        else
            System.out.println("User was not added to DB");
    }

    @Override
    public void remove(UserPOCO userPOCO) {
        int result = 0;
        try {
            result = stm.executeUpdate("DELETE from users WHERE id = " + userPOCO.id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (result == 0)
            System.out.println("User was not deleted from DB");
        else
            System.out.println("User deleted successfully");
    }

    @Override
    public void update(UserPOCO userPOCO, long id) {
        int result = 0;
        try {
            result = stm.executeUpdate("UPDATE users SET" +
                    " username=" + userPOCO.username +
                    ", userpasword=" + userPOCO.userpassword +
                    ", email=" + userPOCO.email +
                    ", user_role =" + userPOCO.user_role +
                    " where id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result == 0)
            System.out.println("User was not updated from DB");
        else
            System.out.println("User updated successfully");
    }

    public UserPOCO get_user_by_username(String username){
        UserPOCO user = null;
        try {
            var result = stm.executeQuery("SELECT * FROM get_user_by_username("+username+")");
            while (result.next()) {
                user =
                        new UserPOCO(result.getLong("id"),
                                result.getString("username"),
                                result.getString("userpassword"),
                                result.getString("email"),
                                result.getInt("user_role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public UserPOCO log_in(String username,String userpassword){
        UserPOCO user = null;
        String query = "SELECT * FROM users where username=\""+username+"\" AND userpassword=\""+userpassword+"\"";
        try {
            var result = stm.executeQuery(query);
            while (result.next()) {
                user =
                        new UserPOCO(result.getLong("id"),
                                result.getString("username"),
                                result.getString("userpassword"),
                                result.getString("email"),
                                result.getInt("user_role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


}
