package DB_Repo._POCO;

public class AirlineCompanyPOCO implements POCO{
    public long id;
    public String company_name;
    public int country_id;
    public long user_id;

    public AirlineCompanyPOCO(long id, String name, int country_id, long user_id) {
        this.id = id;
        this.company_name = name;
        this.country_id = country_id;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "AirlineCompanyPOCO{" +
                "airline_id=" + id +
                ", name='" + company_name + '\'' +
                ", country_id=" + country_id +
                ", user_id=" + user_id +
                '}';
    }
}
