package DB_Repo._POCO;

import javax.print.DocFlavor;

public class UserPOCO implements POCO{

    public long id;
    public String username;
    public String userpassword;
    public String email;
    public int user_role;

    public UserPOCO(long id, String username, String userpassword, String email, int user_role) {
        this.id = id;
        this.username = username;
        this.userpassword = userpassword;
        this.email = email;
        this.user_role = user_role;
    }

    @Override
    public String toString() {
        return "UserPOCO{" +
                "user_id=" + id +
                ", username='" + username + '\'' +
                ", userpassword='" + userpassword + '\'' +
                ", email='" + email + '\'' +
                ", user_role_id=" + user_role +
                '}';
    }
}
