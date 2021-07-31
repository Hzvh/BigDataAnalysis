package bean;

public class Intimacy {

    //SELECT id,intimacy_rank,contact_id1,contact_id2,call_count,call_duration_count FROM tb_intimacy;
    private String id;
    private String intimacy_rank;
    private String contact_id1;
    private String contact_id2;
    private String call_count;
    private String call_duration_count;
    private String name1;
    private String name2;
    private String telephone;

    public Intimacy() {
    }

    public Intimacy(String id, String intimacy_rank, String contact_id1, String contact_id2, String call_count, String call_duration_count, String name1, String name2, String telephone) {
        this.id = id;
        this.intimacy_rank = intimacy_rank;
        this.contact_id1 = contact_id1;
        this.contact_id2 = contact_id2;
        this.call_count = call_count;
        this.call_duration_count = call_duration_count;
        this.name1 = name1;
        this.name2 = name2;
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Intimacy{" +
                "id='" + id + '\'' +
                ", intimacy_rank='" + intimacy_rank + '\'' +
                ", contact_id1='" + contact_id1 + '\'' +
                ", contact_id2='" + contact_id2 + '\'' +
                ", call_count='" + call_count + '\'' +
                ", call_duration_count='" + call_duration_count + '\'' +
                ", name1='" + name1 + '\'' +
                ", name2='" + name2 + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntimacy_rank() {
        return intimacy_rank;
    }

    public void setIntimacy_rank(String intimacy_rank) {
        this.intimacy_rank = intimacy_rank;
    }

    public String getContact_id1() {
        return contact_id1;
    }

    public void setContact_id1(String contact_id1) {
        this.contact_id1 = contact_id1;
    }

    public String getContact_id2() {
        return contact_id2;
    }

    public void setContact_id2(String contact_id2) {
        this.contact_id2 = contact_id2;
    }

    public String getCall_count() {
        return call_count;
    }

    public void setCall_count(String call_count) {
        this.call_count = call_count;
    }

    public String getCall_duration_count() {
        return call_duration_count;
    }

    public void setCall_duration_count(String call_duration_count) {
        this.call_duration_count = call_duration_count;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
