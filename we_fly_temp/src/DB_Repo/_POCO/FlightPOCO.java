package DB_Repo._POCO;

public class FlightPOCO implements POCO{
    public long id;
    public long airline_company_id;
    public int origin_country_id;
    public int destination_country_id;
    public String departure_time;
    public String landing_time;
    public int remaining_tickets;

    public FlightPOCO(long id, long airline_company_id, int origin_country_id, int destination_country_id, String departure_time, String landing_time, int remaining_tickets) {
        this.id = id;
        this.airline_company_id = airline_company_id;
        this.origin_country_id = origin_country_id;
        this.destination_country_id = destination_country_id;
        this.departure_time = departure_time;
        this.landing_time = landing_time;
        this.remaining_tickets = remaining_tickets;
    }

    @Override
    public String toString() {
        return "FlightPOCO{" +
                "flight_id=" + id +
                ", airline_company_id=" + airline_company_id +
                ", origin_country_id=" + origin_country_id +
                ", destination_country_id=" + destination_country_id +
                ", departure_time='" + departure_time + '\'' +
                ", landing_time='" + landing_time + '\'' +
                ", remaining_tickets=" + remaining_tickets +
                '}';
    }
}
