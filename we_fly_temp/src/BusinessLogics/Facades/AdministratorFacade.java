package BusinessLogics.Facades;

import BusinessLogics.LoginToken;
import DB_Repo._DAO.CustomersDAO;
import DB_Repo._POCO.AdministratorPOCO;
import DB_Repo._POCO.AirlineCompanyPOCO;
import DB_Repo._POCO.CustomerPOCO;

import java.util.List;

public class AdministratorFacade extends AnonymousFacade{
    LoginToken user_token;
    AdministratorPOCO my_admin;

    public AdministratorFacade(LoginToken token, AdministratorPOCO adminPOCO) {
        this.user_token = token;
        this.my_admin = adminPOCO;
    }

    // As an admin you can add whatever values without validation.

    public List get_all_customers(){
        return super.customersDAO.getAll();
    }
    public void add_airline(AirlineCompanyPOCO airline){
        super.airlineCompanyDAO.add(airline);
    }
    public void add_customer(CustomerPOCO customer){
        super.customersDAO.add(customer);
    }
    public void add_administrator(AdministratorPOCO new_admin){
        super.administratorDAO.add(new_admin);
    }
    public void remove_airline(AirlineCompanyPOCO airline){
        super.airlineCompanyDAO.remove(airline);
    }
    public void remove_customer(CustomerPOCO customer){
        super.customersDAO.remove(customer);
    }
    public void remove_administrator(AdministratorPOCO admin){
        super.administratorDAO.remove(admin);
    }

}
