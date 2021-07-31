package bean;

public class QueryList {
    private String id;
    private String telephone;
    private String name;
    private String id_contact;
    private String time;

    public QueryList() {
    }

    public QueryList(String id, String telephone, String name, String id_contact, String time) {
        this.id = id;
        this.telephone = telephone;
        this.name = name;
        this.id_contact = id_contact;
        this.time = time;
    }

    @Override
    public String toString() {
        return "QueryList{" +
                "id='" + id + '\'' +
                ", telephone='" + telephone + '\'' +
                ", name='" + name + '\'' +
                ", id_contact='" + id_contact + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_contact() {
        return id_contact;
    }

    public void setId_contact(String id_contact) {
        this.id_contact = id_contact;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
