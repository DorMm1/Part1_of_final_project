package DB_Repo._DAO;

import DB_Repo.PostgreSQLConnection;
import DB_Repo._POCO.User_RolesPOCO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class User_RolesDAO implements DAO<User_RolesPOCO> {

    List<User_RolesPOCO> user_rolesPOCOList = new ArrayList<>();
    PostgreSQLConnection postgreSQLConnection = new PostgreSQLConnection();
    Connection connection = postgreSQLConnection.getConnection();
    Statement stm = postgreSQLConnection.getStatement();

    @Override
    public User_RolesPOCO get(long id) {
        User_RolesPOCO user_role = null;
        try {
            var result = stm.executeQuery("SELECT * FROM user_roles WHERE id =" + id);
            while (result.next()) {
                user_role =
                        new User_RolesPOCO(result.getInt("id"),
                                result.getString("role_name")
                        );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user_role;
    }

    @Override
    public List getAll() {
        try {
            var result = stm.executeQuery("SELECT * FROM user_roles");
            while (result.next()) {
                user_rolesPOCOList.add(
                        new User_RolesPOCO(result.getInt("id"),
                                result.getString("role_name")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user_rolesPOCOList;
    }

    @Override
    public void add(User_RolesPOCO user_rolesPOCO) {
        int result = 0;
        try {
            result = stm.executeUpdate("INSERT INTO user_roles (role_name) " +
                    "VALUES" +
                    "('" + user_rolesPOCO.role_name + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result != 0)
            System.out.println("User_Role added successfully");
        else
            System.out.println("User_Role was not added to DB");
    }

    @Override
    public void remove(User_RolesPOCO user_rolesPOCO) {
        int result = 0;
        try {
            result = stm.executeUpdate("DELETE from user_roles WHERE id = " + user_rolesPOCO.id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (result == 0)
            System.out.println("User_Role was not deleted from DB");
        else
            System.out.println("User_Role deleted successfully");
    }

    @Override
    public void update(User_RolesPOCO user_rolesPOCO, long id) {
        int result = 0;
        try {
            result = stm.executeUpdate("UPDATE users SET" +
                    " role_name=" + user_rolesPOCO.role_name +
                    " where id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result == 0)
            System.out.println("User_Role was not updated from DB");
        else
            System.out.println("User_Role updated successfully");
    }
}
