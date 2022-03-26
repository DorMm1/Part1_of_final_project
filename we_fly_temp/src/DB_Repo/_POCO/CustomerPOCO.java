package DB_Repo._POCO;

public class CustomerPOCO implements POCO{

    public long id;
    public String first_name;
    public String last_name;
    public String address;
    public String phone_no;
    public String credit_card_no;
    public long user_id;

    public CustomerPOCO(long id, String first_name, String last_name, String address, String phone_no, String credit_card_no, long user_id) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.phone_no = phone_no;
        this.credit_card_no = credit_card_no;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "CustomerPOCO{" +
                "customer_id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", address='" + address + '\'' +
                ", phone_no='" + phone_no + '\'' +
                ", credit_card_no='" + credit_card_no + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
