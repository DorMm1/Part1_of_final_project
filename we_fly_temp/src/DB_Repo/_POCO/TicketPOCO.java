package DB_Repo._POCO;

public class TicketPOCO implements POCO{

    public long id;
    public long flight_id;
    public long customer_id;

    public TicketPOCO(long id, long flight_id, long customer_id) {
        this.id = id;
        this.flight_id = flight_id;
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        return "TicketPOCO{" +
                "ticket_id=" + id +
                ", flight_id=" + flight_id +
                ", customer_id=" + customer_id +
                '}';
    }
}
