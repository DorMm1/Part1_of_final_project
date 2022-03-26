package DB_Repo._POCO;

public class AdministratorPOCO implements POCO{

    public int id;
    public String first_name;
    public String last_name;
    public long user_id;

    public AdministratorPOCO(int id, String first_name, String last_name, long user_id) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "AdministratorPOCO{" +
                "admin_id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
