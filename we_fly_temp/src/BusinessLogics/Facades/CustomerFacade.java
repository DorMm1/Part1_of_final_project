package BusinessLogics.Facades;

import BusinessLogics.LoginToken;
import DB_Repo._DAO.CustomersDAO;
import DB_Repo._DAO.FlightsDAO;
import DB_Repo._DAO.TicketsDAO;
import DB_Repo._POCO.CustomerPOCO;
import DB_Repo._POCO.TicketPOCO;

import java.util.List;

public class CustomerFacade extends AnonymousFacade {

    CustomersDAO customersDAO;
    TicketsDAO ticketsDAO;
    FlightsDAO flightsDAO;
    LoginToken user_token;
    CustomerPOCO my_customer;

    public CustomerFacade(LoginToken token, CustomerPOCO customerPOCO) {
        this.customersDAO = new CustomersDAO();
        this.ticketsDAO = new TicketsDAO();
        this.flightsDAO = new FlightsDAO();
        this.user_token = token;
        this.my_customer = customerPOCO;

    }

    //Need to fix this architecture it does not meet the requirements
    private void create_new_customer(String firstname, String lastname, String address, String phone_no, String credit_card_no) {
        this.my_customer = new CustomerPOCO(555, firstname, lastname, address, phone_no, credit_card_no, this.user_token.getMyself().id);
    }

    public void update_customer(CustomerPOCO new_customer, long current_customer_id) {
        if (my_customer.user_id == current_customer_id) customersDAO.update(new_customer, current_customer_id);
        else System.out.println("You can only edit yourself");
    }

    public void add_ticket(TicketPOCO new_ticket) {
        if (new_ticket.customer_id == my_customer.id) {
            int remaining_tickets = flightsDAO.get_remaining_tickets(new_ticket.flight_id);
            if (remaining_tickets > 0) {
                flightsDAO.decrement_remaining_tickets(new_ticket.flight_id);
                ticketsDAO.add(new_ticket);
                System.out.println("Ticket id =" + new_ticket.id + " added");
            } else {
                System.out.println("Flight is full, ticket was not added.");
            }
        } else System.out.println("You can only add ticket for yourself");
    }

    public void remove_ticket(TicketPOCO new_ticket) {
        List current_customer_ticket_list = get_my_tickets();
        if (current_customer_ticket_list.contains(new_ticket)) {
            ticketsDAO.remove(new_ticket);
            flightsDAO.increment_remaining_tickets(new_ticket.flight_id);
            System.out.println("Ticket id =" + new_ticket.id + " deleted");
        } else System.out.println("You can't remove a ticket you don't own");
    }

    public List get_my_tickets() {
        return ticketsDAO.get_tickets_by_customer(my_customer.id);
    }

}
