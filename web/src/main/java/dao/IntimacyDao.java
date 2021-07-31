package dao;

import bean.Intimacy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Repository
public class IntimacyDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @RequestMapping
    public List<Intimacy> getIntimacy(HashMap<String, String> hashMap){
//        String sql ="SELECT * from tb_intimacy WHERE tb_intimacy.contact_id1 IN(SELECT id FROM tb_contacts WHERE telephone=:telephone);";
//        String sql ="SELECT DISTINCT id intimacy_rank,contact_id1,`name1`,contact_id2,`name2`,call_count,call_duration_count from tb_intimacy,(SELECT * from (SELECT `name` name1,tb_intimacy.contact_id1 c1 from tb_contacts,tb_intimacy WHERE tb_intimacy.contact_id1 = tb_contacts.id) t1,(SELECT `name` name2,tb_intimacy.contact_id2 c2 from tb_contacts,tb_intimacy WHERE tb_intimacy.contact_id2 = tb_contacts.id) t2) t3 WHERE t3.c1=tb_intimacy.contact_id1 AND t3.c2 = tb_intimacy.contact_id2;";
       String sql = "SELECT * FROM (SELECT DISTINCT id,intimacy_rank,contact_id1,`name1`,telephone,contact_id2,`name2`,call_count,call_duration_count from tb_intimacy,(SELECT * from (SELECT `name` name1,tb_intimacy.contact_id1 c1,telephone from tb_contacts,tb_intimacy WHERE tb_intimacy.contact_id1 = tb_contacts.id) t1,(SELECT `name` name2,tb_intimacy.contact_id2 c2 from tb_contacts,tb_intimacy WHERE tb_intimacy.contact_id2 = tb_contacts.id) t2) t3 WHERE t3.c1=tb_intimacy.contact_id1 AND t3.c2 = tb_intimacy.contact_id2) t5 WHERE telephone=:telephone;";
        BeanPropertyRowMapper<Intimacy> intimacyBeanPropertyRowMapper = new BeanPropertyRowMapper<>(Intimacy.class);
        List<Intimacy> intimacyList = namedParameterJdbcTemplate.query(sql, hashMap, intimacyBeanPropertyRowMapper);
        return intimacyList;
    }
}
