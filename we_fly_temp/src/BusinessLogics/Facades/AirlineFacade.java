package BusinessLogics.Facades;

import BusinessLogics.LoginToken;
import DB_Repo._DAO.FlightsDAO;
import DB_Repo._POCO.AirlineCompanyPOCO;
import DB_Repo._POCO.FlightPOCO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class AirlineFacade extends AnonymousFacade {
    LoginToken user_token;
    AirlineCompanyPOCO my_airline;
    List<FlightPOCO> my_flights;
    FlightsDAO flightsDAO;

    public AirlineFacade(LoginToken token, AirlineCompanyPOCO airline) {
        this.user_token = token;
        this.my_airline = airline;
        this.my_flights = new ArrayList<>();
        flightsDAO = new FlightsDAO();
    }

    public List get_my_flights() {
        my_flights = flightsDAO.get_flights_by_airline_id(my_airline.id);
        return my_flights;
    }

    public void update_airline(AirlineCompanyPOCO airlineCompanyPOCO) {
        super.airlineCompanyDAO.update(airlineCompanyPOCO, this.my_airline.id);
    }

    public void add_flight(FlightPOCO flightPOCO) {
        if (check_flight_values(flightPOCO) && my_airline.id == flightPOCO.airline_company_id) {
            flightsDAO.add(flightPOCO);
            this.my_flights.add(flightPOCO);
        } else System.out.println("flight values invalid");
    }

    public void update_flight(FlightPOCO flightPOCO) {
        if (check_flight_values(flightPOCO) && my_airline.id == flightPOCO.airline_company_id)
            flightsDAO.update(flightPOCO, flightPOCO.id);
    }

    public void update_flight_by_id(FlightPOCO flightPOCO, long id) {
        if (check_flight_values(flightPOCO) && this.get_my_flights().contains(flightsDAO.get(id)))
            flightsDAO.update(flightPOCO, id);
    }

    public void remove_flight(FlightPOCO flightPOCO) {
        if (this.get_my_flights().contains(flightPOCO)) {
            flightsDAO.remove(flightPOCO);
            this.my_flights.remove(flightPOCO);
        }
    }

    private boolean check_flight_values(FlightPOCO flightPOCO) {
        boolean isValid = true;
        if (flightPOCO.airline_company_id != my_airline.id) {
            System.out.println("Failure: You cannot add a flight to a different airline");
            isValid = false;
        }
        if (Timestamp.valueOf(flightPOCO.departure_time).after(Timestamp.valueOf(flightPOCO.landing_time))) {
            System.out.println("Failure: invalid departure/arrival time");
            isValid = false;
        }
        if (flightPOCO.destination_country_id == flightPOCO.origin_country_id) {
            System.out.println("Failure: invalid destination and origin country values");
            isValid = false;
        }
        if (flightPOCO.remaining_tickets < 0) {
            System.out.println("Failure: invalid remaining_tickets values");
            isValid = false;
        }
        return isValid;
    }


}
