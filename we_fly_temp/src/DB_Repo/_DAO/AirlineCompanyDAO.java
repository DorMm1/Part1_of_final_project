package DB_Repo._DAO;

import DB_Repo._POCO.AirlineCompanyPOCO;
import DB_Repo.PostgreSQLConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AirlineCompanyDAO implements DAO<AirlineCompanyPOCO> {

    List<AirlineCompanyPOCO> airlineCompanyPOCOList = new ArrayList<>();
    PostgreSQLConnection postgreSQLConnection = new PostgreSQLConnection();
    Connection connection = postgreSQLConnection.getConnection();
    Statement stm = postgreSQLConnection.getStatement();

    @Override
    public AirlineCompanyPOCO get(long id) {
        AirlineCompanyPOCO airline = null;
        try {
            var result = stm.executeQuery("SELECT * FROM airline_companies WHERE id =" + id);
            while (result.next()) {
                airline = new AirlineCompanyPOCO(
                        result.getLong("id"),
                        result.getString("company_name"),
                        result.getInt("country_id"),
                        result.getLong("user_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airline;
    }

    @Override
    public List getAll() {
        try {
            var result = stm.executeQuery("SELECT * FROM airline_companies");
            while (result.next()) {
                airlineCompanyPOCOList.add(new AirlineCompanyPOCO(
                        result.getLong("id"),
                        result.getString("company_name"),
                        result.getInt("country_id"),
                        result.getLong("user_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlineCompanyPOCOList;
    }

    @Override
    public void add(AirlineCompanyPOCO airlineCompanyPOCO) {
        int result = 0;
        try {
            result = stm.executeUpdate("INSERT INTO airline_companies (company_name,country_id,user_id) " +
                    "VALUES" +
                    "('" + airlineCompanyPOCO.company_name + "'," + airlineCompanyPOCO.country_id + "," + airlineCompanyPOCO.user_id + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result != 0)
            System.out.println("Airline_Company added successfully");
        else
            System.out.println("Airline_Company was not added to DB");
    }

    @Override
    public void remove(AirlineCompanyPOCO airlineCompanyPOCO) {
        int result = 0;
        try {
            result = stm.executeUpdate("DELETE from airline_companies WHERE id = " + airlineCompanyPOCO.id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (result == 0)
            System.out.println("Airline_Company was not deleted from DB");
        else
            System.out.println("Airline_Company deleted successfully");
    }

    @Override
    public void update(AirlineCompanyPOCO airlineCompanyPOCO, long id) {
        int result = 0;
        try {
            result = stm.executeUpdate("UPDATE airline_companies SET" +
                    " company_name=" + airlineCompanyPOCO.company_name +
                    ", country_id=" + airlineCompanyPOCO.country_id +
                    ", user_id=" + airlineCompanyPOCO.user_id +
                    " where id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result == 0)
            System.out.println("Airline_Company was not updated from DB");
        else
            System.out.println("Airline_Company updated successfully");
    }

    public AirlineCompanyPOCO get_airline_by_username(String username){
        AirlineCompanyPOCO airline = null;
        try {
            var result = stm.executeQuery("SELECT * FROM get_airline_by_username("+username+")");
            while (result.next()) {
                airline = new AirlineCompanyPOCO(
                        result.getLong("id"),
                        result.getString("company_name"),
                        result.getInt("country_id"),
                        result.getLong("user_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airline;
    }

}
