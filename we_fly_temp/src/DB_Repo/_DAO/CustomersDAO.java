package DB_Repo._DAO;

import DB_Repo.PostgreSQLConnection;
import DB_Repo._POCO.CustomerPOCO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomersDAO implements DAO<CustomerPOCO> {

    List<CustomerPOCO> customersPOCOList = new ArrayList<>();
    PostgreSQLConnection postgreSQLConnection = new PostgreSQLConnection();
    Connection connection = postgreSQLConnection.getConnection();
    Statement stm = postgreSQLConnection.getStatement();

    @Override
    public CustomerPOCO get(long id) {
        CustomerPOCO customer = null;
        try {
            var result = stm.executeQuery("SELECT * FROM customers WHERE id =" + id);
            while (result.next()) {
                customer = new CustomerPOCO(
                        result.getLong("id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("address"),
                        result.getString("phone_no"),
                        result.getString("credit_card_no"),
                        result.getLong("user_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public List getAll() {
        try {
            var result = stm.executeQuery("SELECT * FROM users");
            while (result.next()) {
                customersPOCOList.add(
                        new CustomerPOCO(
                                result.getLong("id"),
                                result.getString("first_name"),
                                result.getString("last_name"),
                                result.getString("address"),
                                result.getString("phone_no"),
                                result.getString("credit_card_no"),
                                result.getLong("user_id")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customersPOCOList;
    }

    @Override
    public void add(CustomerPOCO customerPOCO) {
        int result = 0;
        try {
            result = stm.executeUpdate("INSERT INTO administrators (first_name,last_name,address,phone_no," +
                    "credit_card_no,user_id) " +
                    "VALUES" +
                    "('" + customerPOCO.first_name + "','" + customerPOCO.last_name + "','"
                    + customerPOCO.address + "','" + customerPOCO.phone_no + "','"
                    + customerPOCO.credit_card_no + "'," + customerPOCO.user_id + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result != 0)
            System.out.println("Customer added successfully");
        else
            System.out.println("Customer was not added to DB");

    }

    @Override
    public void remove(CustomerPOCO customerPOCO) {
        int result = 0;
        try {
            result = stm.executeUpdate("DELETE from customers WHERE id = " + customerPOCO.id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (result == 0)
            System.out.println("Customer was not deleted from DB");
        else
            System.out.println("Customer deleted successfully");
    }

    @Override
    public void update(CustomerPOCO customerPOCO, long id) {
        int result = 0;
        try {
            result = stm.executeUpdate("UPDATE users SET" +
                    " first_name=" + customerPOCO.first_name +
                    ", last_name=" + customerPOCO.last_name +
                    ", address=" + customerPOCO.address +
                    ", phone_no =" + customerPOCO.phone_no +
                    ", credit_card_no =" + customerPOCO.credit_card_no +
                    ", user_id =" + customerPOCO.user_id +
                    " where id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result == 0)
            System.out.println("Customer was not updated from DB");
        else
            System.out.println("Customer updated successfully");
    }

    public CustomerPOCO get_customer_by_username(String username){
        CustomerPOCO customer = null;
        try {
            var result = stm.executeQuery("SELECT * FROM get_customer_by_username("+username+")");
            while (result.next()) {
                customer = new CustomerPOCO(
                        result.getLong("id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("address"),
                        result.getString("phone_no"),
                        result.getString("credit_card_no"),
                        result.getLong("user_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
    public CustomerPOCO get_customer_by_user_id(long id) {
        CustomerPOCO customer = null;
        try {
            var result = stm.executeQuery("SELECT * FROM customers WHERE user_id =" + id);
            while (result.next()) {
                customer = new CustomerPOCO(
                        result.getLong("id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("address"),
                        result.getString("phone_no"),
                        result.getString("credit_card_no"),
                        result.getLong("user_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

}
