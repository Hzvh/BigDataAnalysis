package dao;

import bean.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ContactDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Contact> getContacts(){
        String sql = "SELECT * FROM tb_contacts,tb_intimacy WHERE tb_contacts.id=tb_intimacy.contact_id1 AND telephone=:telephone;";
        BeanPropertyRowMapper<Contact> contactBeanPropertyRowMapper = new BeanPropertyRowMapper<>(Contact.class);
        List<Contact> contactList = jdbcTemplate.query(sql, contactBeanPropertyRowMapper);
        return contactList;
    }

    public List<Contact> getContactWithTel(HashMap<String, String> hashMap){
        String sql = "SELECT * from tb_intimacy WHERE tb_intimacy.contact_id1 IN(SELECT id FROM tb_contacts WHERE telephone=15596505995);";
        BeanPropertyRowMapper<Contact> contactBeanPropertyRowMapper = new BeanPropertyRowMapper<>(Contact.class);
        List<Contact> contactList = namedParameterJdbcTemplate.query(sql, hashMap, contactBeanPropertyRowMapper);
        return contactList;
    }
}
