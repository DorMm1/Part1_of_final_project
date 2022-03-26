package BusinessLogics.Facades;

import DB_Repo._DAO.*;
import DB_Repo._POCO.*;


import java.util.List;



public abstract class BaseFacade {

    private FlightsDAO flights = new FlightsDAO();
    private AirlineCompanyDAO airlines = new AirlineCompanyDAO();
    private CountryDAO countries = new CountryDAO();
    private UsersDAO users = new UsersDAO();

    public List get_all_flights(){
        return flights.getAll();
    }
    public FlightPOCO get_flight_by_id(long id){
        return flights.get(id);
    }
    public List get_flights_by_parameters(int origin_country_id, int destination_country_id, String date){
        return flights.get_flights_by_parameters(origin_country_id,destination_country_id,date);
    }

    public List get_all_airlines(){
        return airlines.getAll();
    }
    public AirlineCompanyPOCO get_airline_by_id(long id){
        return airlines.get(id);
    }
    //by username
    public AirlineCompanyPOCO get_airline_by_parameters(String name){
        return airlines.get_airline_by_username(name);
    }

    public List get_all_countries(){
        return countries.getAll();
    }
    public CountryPOCO get_country_by_id(int id){
        return countries.get(id);
    }

    public void create_new_user(UserPOCO new_user){
        users.add(new_user);
    }



}
