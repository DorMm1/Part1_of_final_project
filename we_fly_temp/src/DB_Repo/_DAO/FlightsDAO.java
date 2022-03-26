package DB_Repo._DAO;

import DB_Repo.PostgreSQLConnection;
import DB_Repo._POCO.FlightPOCO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FlightsDAO implements DAO<FlightPOCO> {

    List<FlightPOCO> flightPOCOList = new ArrayList<>();
    PostgreSQLConnection postgreSQLConnection = new PostgreSQLConnection();
    Connection connection = postgreSQLConnection.getConnection();
    Statement stm = postgreSQLConnection.getStatement();

    @Override
    public FlightPOCO get(long id) {
        FlightPOCO flight = null;
        try {
            var result = stm.executeQuery("SELECT * FROM flights WHERE id =" + id);
            while (result.next()) {
                flight =
                        new FlightPOCO(result.getLong("id"),
                                result.getLong("airline_company_id"),
                                result.getInt("origin_country_id"),
                                result.getInt("destination_country_id"),
                                result.getString("departure_time"),
                                result.getString("landing_time"),
                                result.getInt("remaining_tickets")
                        );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }

    @Override
    public List getAll() {
        try {
            var result = stm.executeQuery("SELECT * FROM flights");
            while (result.next()) {
                flightPOCOList.add(
                        new FlightPOCO(result.getLong("id"),
                                result.getLong("airline_company_id"),
                                result.getInt("origin_country_id"),
                                result.getInt("destination_country_id"),
                                result.getString("departure_time"),
                                result.getString("landing_time"),
                                result.getInt("remaining_tickets")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightPOCOList;
    }

    @Override
    public void add(FlightPOCO flightPOCO) {
        int result = 0;
        try {
            result = stm.executeUpdate("INSERT INTO flights (airline_company_id, origin_country_id," +
                    " destination_country_id, departure_time, landing_time, remaining_tickets) " +
                    "VALUES" +
                    "(" + flightPOCO.airline_company_id + "," + flightPOCO.origin_country_id + ","
                    + flightPOCO.destination_country_id + ",'" + flightPOCO.departure_time + "','"
                    + flightPOCO.landing_time + "'," + flightPOCO.remaining_tickets + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result != 0)
            System.out.println("Flight added successfully");
        else
            System.out.println("Flight was not added to DB");
    }

    @Override
    public void remove(FlightPOCO flightPOCO) {
        int result = 0;
        try {
            result = stm.executeUpdate("DELETE from flights WHERE id = " + flightPOCO.id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (result == 0)
            System.out.println("Flight was not deleted from DB");
        else
            System.out.println("Flight deleted successfully");
    }

    @Override
    public void update(FlightPOCO flightPOCO, long id) {
        int result = 0;
        try {
            result = stm.executeUpdate("UPDATE flights SET" +
                    " airline_company_id=" + flightPOCO.airline_company_id +
                    ", origin_country_id=" + flightPOCO.origin_country_id +
                    ", destination_country_id=" + flightPOCO.destination_country_id +
                    ", departure_time =" + flightPOCO.departure_time +
                    ", landing_time =" + flightPOCO.landing_time +
                    ", remaining_tickets =" + flightPOCO.remaining_tickets +
                    " where id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result == 0)
            System.out.println("Flight was not updated from DB");
        else
            System.out.println("Flight updated successfully");
    }

    public List get_flights_by_parameters(int _origin_country_id, int _destination_country_id, String _date) {
        ArrayList<FlightPOCO> localList = new ArrayList<>();
        try {
            var result = stm.executeQuery("SELECT * FROM get_flights_by_parameters(" + _origin_country_id +
                    "," + _destination_country_id + "," + _date + ")");
            while (result.next()) {
                localList.add(
                        new FlightPOCO(result.getLong("id"),
                                result.getLong("airline_company_id"),
                                result.getInt("origin_country_id"),
                                result.getInt("destination_country_id"),
                                result.getString("departure_time"),
                                result.getString("landing_time"),
                                result.getInt("remaining_tickets")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return localList;
    }
    public List get_flights_by_airline_id(long _airline_id) {
        ArrayList<FlightPOCO> localList = new ArrayList<>();
        try {
            var result = stm.executeQuery("SELECT * FROM get_flights_by_parameters(" + _airline_id+ ")");
            while (result.next()) {
                localList.add(
                        new FlightPOCO(result.getLong("id"),
                                result.getLong("airline_company_id"),
                                result.getInt("origin_country_id"),
                                result.getInt("destination_country_id"),
                                result.getString("departure_time"),
                                result.getString("landing_time"),
                                result.getInt("remaining_tickets")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return localList;
    }
    public List get_arrival_flights(long _country_id) {
        ArrayList<FlightPOCO> localList = new ArrayList<>();
        try {
            var result = stm.executeQuery("SELECT * FROM get_arrival_flights(" + _country_id+ ")");
            while (result.next()) {
                localList.add(
                        new FlightPOCO(result.getLong("id"),
                                result.getLong("airline_company_id"),
                                result.getInt("origin_country_id"),
                                result.getInt("destination_country_id"),
                                result.getString("departure_time"),
                                result.getString("landing_time"),
                                result.getInt("remaining_tickets")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return localList;
    }
    public List get_departure_flights(long _country_id) {
        ArrayList<FlightPOCO> localList = new ArrayList<>();
        try {
            var result = stm.executeQuery("SELECT * FROM get_departure_flights(" + _country_id+ ")");
            while (result.next()) {
                localList.add(
                        new FlightPOCO(result.getLong("id"),
                                result.getLong("airline_company_id"),
                                result.getInt("origin_country_id"),
                                result.getInt("destination_country_id"),
                                result.getString("departure_time"),
                                result.getString("landing_time"),
                                result.getInt("remaining_tickets")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return localList;
    }
    public int get_remaining_tickets(long flight_id){
        int tickets_amount=0;
        String query = "SELECT remaining_tickets FROM flights where id = "+flight_id;
        try {
            var result = stm.executeQuery(query);
                tickets_amount = result.getInt("remaining_tickets");
        } catch (SQLException e) {
            e.printStackTrace();
            tickets_amount=-1;
        }
        return tickets_amount;
    }
    public void increment_remaining_tickets(long flight_id){
        String query = "UPDATE flights SET remaining_tickets = remaining_tickets + 1" +
                "WHERE id = "+flight_id;
        try {
            var result = stm.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ticket not found");
        }
    }
    public void decrement_remaining_tickets(long flight_id){
        String query = "UPDATE flights SET remaining_tickets = remaining_tickets -1 " +
                "WHERE id = "+flight_id;
        try {
            var result = stm.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ticket not found");
        }
    }
}
