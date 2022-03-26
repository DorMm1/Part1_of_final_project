package DB_Repo._DAO;

import DB_Repo.PostgreSQLConnection;
import DB_Repo._POCO.TicketPOCO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketsDAO implements DAO<TicketPOCO> {

    List<TicketPOCO> ticketPOCOList = new ArrayList<>();
    PostgreSQLConnection postgreSQLConnection = new PostgreSQLConnection();
    Connection connection = postgreSQLConnection.getConnection();
    Statement stm = postgreSQLConnection.getStatement();

    @Override
    public TicketPOCO get(long id) {
        TicketPOCO ticket = null;
        try {
            var result = stm.executeQuery("SELECT * FROM tickets WHERE id =" + id);
            while (result.next()) {
                ticket =
                        new TicketPOCO(result.getLong("id"),
                                result.getLong("flight_id"),
                                result.getLong("customer_id")
                        );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    @Override
    public List getAll() {
        try {
            var result = stm.executeQuery("SELECT * FROM tickets");
            while (result.next()) {
                ticketPOCOList.add(
                        new TicketPOCO(result.getLong("id"),
                                result.getLong("flight_id"),
                                result.getLong("customer_id")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketPOCOList;
    }

    @Override
    public void add(TicketPOCO ticketPOCO) {
        int result = 0;
        try {
            result = stm.executeUpdate("INSERT INTO flights (flight_id, customer_id) " +
                    "VALUES" +
                    "(" + ticketPOCO.flight_id + "," + ticketPOCO.customer_id + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result != 0)
            System.out.println("Ticket added successfully");
        else
            System.out.println("Ticket was not added to DB");
    }

    @Override
    public void remove(TicketPOCO ticketPOCO) {
        int result = 0;
        try {
            result = stm.executeUpdate("DELETE from flights WHERE id = " + ticketPOCO.id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (result == 0)
            System.out.println("Ticket was not deleted from DB");
        else
            System.out.println("Ticket deleted successfully");
    }

    @Override
    public void update(TicketPOCO ticketPOCO, long id) {
        int result = 0;
        try {
            result = stm.executeUpdate("UPDATE flights SET" +
                    " flight_id=" + ticketPOCO.flight_id +
                    ", customer_id=" + ticketPOCO.customer_id +
                    " where id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result == 0)
            System.out.println("Ticket was not updated from DB");
        else
            System.out.println("Ticket updated successfully");
    }
    public List get_tickets_by_customer(long _customer_id) {
        ArrayList<TicketPOCO> local_tickets_list = new ArrayList<>();
        try {
            var result = stm.executeQuery("SELECT * FROM get_tickets_by_customer("+_customer_id+")");
            while (result.next()) {
                local_tickets_list.add(
                        new TicketPOCO(result.getLong("id"),
                                result.getLong("flight_id"),
                                result.getLong("customer_id")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return local_tickets_list;
    }


}
