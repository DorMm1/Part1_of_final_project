package BusinessLogics.Facades;

import BusinessLogics.LoginToken;
import DB_Repo._DAO.*;
import DB_Repo._POCO.*;

import java.util.regex.Pattern;


public class AnonymousFacade extends BaseFacade{

    UsersDAO usersDAO;
    CustomersDAO customersDAO;
    AirlineCompanyDAO airlineCompanyDAO;
    AdministratorDAO administratorDAO;

    public AnonymousFacade() {
        this.usersDAO = new UsersDAO();
        this.customersDAO = new CustomersDAO();
        this.airlineCompanyDAO = new AirlineCompanyDAO();
        this.administratorDAO = new AdministratorDAO();
    }
    private UserPOCO get_user_login(String username, String password){
        return usersDAO.log_in(username,password);
    }
    private CustomerPOCO get_customer_by_user(UserPOCO userPOCO){
        CustomerPOCO customer = customersDAO.get_customer_by_user_id(userPOCO.id);
        if(customer!=null) return customer;
        else{
           customer = add_customer("input","from","HTML","login","page",userPOCO.id);
        }
        return customer;
    }
    private AirlineCompanyPOCO get_airline_by_user(UserPOCO userPOCO){
        return airlineCompanyDAO.get_airline_by_username(userPOCO.username);
    }
    private AdministratorPOCO get_admin_by_user(UserPOCO userPOCO){
        return administratorDAO.get_by_user_id(userPOCO.id);
    }

    public BaseFacade login(String username,String password){
        UserPOCO loginUser = get_user_login(username,password);
        if(loginUser!=null){
            switch (loginUser.user_role){
                case 0:
                    return new CustomerFacade(new LoginToken(loginUser),get_customer_by_user(loginUser));
                case 1:
                    return new AirlineFacade(new LoginToken(loginUser),get_airline_by_user(loginUser));
                case 2:
                    return new AdministratorFacade(new LoginToken(loginUser),get_admin_by_user(loginUser));
            }
        }
        System.out.println("Wrong username / password");
        return null;
    }
    public CustomerPOCO add_customer(String first_name, String last_name, String address, String phone_no, String credit_card_no,long user_id){
        if(first_name.contains("123456789~!#$%^&*(){}[]?><:;|\"") || last_name.contains("123456789~!#$%^&*(){}[]?><:;|\"")){
            System.out.println("invalid name");
            return null;
        }
        if(phone_no.length()!=10){
            System.out.println("phone number must contain 10 digits");
            return null;
        }
        // ID is autoincrement so the value of 555 is irrelevant
        CustomerPOCO new_customer = new CustomerPOCO(555,first_name,last_name,address,phone_no,credit_card_no,user_id);
        customersDAO.add(new_customer);
        return new_customer;
    }
    public UserPOCO create_new_user_account(String username, String password, String email){
        if(username.length()<6){ // username validation
            System.out.println("username must be > 6");
            return null;
        }
        if(!isValidPassword(password)){ // password validation
            System.out.println("password must be > 6, 3 letters and 3 numbers minimum");
            return null;
        }
        if(!isValidEmail(email)){ // email validation
            System.out.println("email is not valid");
            return null;
        }
        return new UserPOCO(555,username,password,email,0);
    }
    //Input validation methods
    private boolean isValidPassword(String password){
        if(password.length()>5 && password.length() < 15) return false;
        int nums=0;
        int letters=0;
        for(int i=0;i<password.length();i++){
            char ch = password.charAt(i);
            if(Character.isSpaceChar(ch)) return false;
            if(Character.isDigit(ch)) nums++;
            if(Character.isLetter(ch)) letters++;
        }
        if(nums>=3 && letters>=3) return true;
        return false;

    }
    private boolean isValidEmail(String email){
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        var match = pattern.matcher(email);
        return match.matches();

    }
}
