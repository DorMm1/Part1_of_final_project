package DB_Repo._POCO;

public class CountryPOCO implements POCO{
    public int id;
    public String country_name;

    public CountryPOCO(int id, String name) {
        this.id = id;
        this.country_name = name;
    }

    @Override
    public String toString() {
        return "CountryPOCO{" +
                "Country_id=" + id +
                ", Country_name='" + country_name + '\'' +
                '}';
    }
}
