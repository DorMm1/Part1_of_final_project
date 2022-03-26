package DB_Repo._DAO;

import DB_Repo.PostgreSQLConnection;
import DB_Repo._POCO.CountryPOCO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO implements DAO<CountryPOCO> {

    List<CountryPOCO> countryPOCOList = new ArrayList<>();
    PostgreSQLConnection postgreSQLConnection = new PostgreSQLConnection();
    Connection connection = postgreSQLConnection.getConnection();
    Statement stm = postgreSQLConnection.getStatement();

    @Override
    public CountryPOCO get(long id) {
        CountryPOCO country = null;
        try {
            var result = stm.executeQuery("SELECT * FROM countries WHERE id =" + id);
            while (result.next()) {
                country = new CountryPOCO(
                        result.getInt("id"),
                        result.getString("country_name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;
    }

    @Override
    public List getAll() {
        try {
            var result = stm.executeQuery("SELECT * FROM countries");
            while (result.next()) {
                countryPOCOList.add(new CountryPOCO(
                        result.getInt("id"),
                        result.getString("country_name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countryPOCOList;
    }

    @Override
    public void add(CountryPOCO countryPOCO) {
        int result = 0;
        try {
            result = stm.executeUpdate("INSERT INTO countries (country_name) " +
                    "VALUES" +
                    "('" + countryPOCO.country_name + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result != 0)
            System.out.println("Country added successfully");
        else
            System.out.println("Country was not added to DB");
    }

    @Override
    public void remove(CountryPOCO countryPOCO) {
        int result = 0;
        try {
            result = stm.executeUpdate("DELETE from countries WHERE id = " + countryPOCO.id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (result == 0)
            System.out.println("Country was not deleted from DB");
        else
            System.out.println("Country deleted successfully");
    }

    @Override
    public void update(CountryPOCO countryPOCO, long id) {
        int result = 0;
        try {
            result = stm.executeUpdate("UPDATE countries SET" +
                    " country_name=" + countryPOCO.country_name +
                    " where id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result == 0)
            System.out.println("Country was not updated from DB");
        else
            System.out.println("Country updated successfully");
    }
}
