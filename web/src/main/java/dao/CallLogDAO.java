package dao;


import bean.CallLog;
import bean.QueryInfo;
import bean.QueryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class CallLogDAO {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public List<CallLog> getCallLogList1(HashMap<String, String> hashMap){
        //1、电话号码 2、年 3、日
        //String sql = "SELECT t3.id_date_contact, t3.id_date_dimension, t3.id_contact, t3.call_sum, t3.call_duration_sum , t3.telephone, t3.name, t4.year, t4.month, t4.day FROM (SELECT t2.id_date_contact, t2.id_date_dimension, t2.id_contact, t2.call_sum, t2.call_duration_sum , t1.telephone, t1.name FROM (SELECT id, telephone, name FROM tb_contacts WHERE telephone = :telephone ) t1 INNER JOIN tb_call t2 ON t1.id = t2.id_contact ) t3 INNER JOIN (SELECT id, year, month, day FROM tb_dimension_date WHERE year = :year AND day = :day ) t4 ON t3.id_date_dimension = t4.id ORDER BY t4.year, t4.month, t4.day;";
        String sql = "SELECT * from (SELECT t3.id_date_contact, t3.id_date_dimension, t3.id_contact, t3.call_sum, t3.call_duration_sum , t3.telephone, t3.name, t4.year, t4.month, t4.day FROM (SELECT t2.id_date_contact, t2.id_date_dimension, t2.id_contact, t2.call_sum, t2.call_duration_sum , t1.telephone, t1.name FROM (SELECT id, telephone, name FROM tb_contacts WHERE telephone = :telephone) t1 INNER JOIN tb_call t2 ON t1.id = t2.id_contact ) t3 INNER JOIN (SELECT id, year, month, day FROM tb_dimension_date WHERE year = :year) t4 ON t3.id_date_dimension = t4.id ORDER BY t4.year, t4.month, t4.day) t5 WHERE t5.`month` !=-1 AND t5.`day` !=-1;";
        BeanPropertyRowMapper<CallLog> contactBeanPropertyRowMapper = new BeanPropertyRowMapper<>(CallLog.class);
        List<CallLog> contactList = namedParameterJdbcTemplate.query(sql, hashMap, contactBeanPropertyRowMapper);
        return contactList;
    }

    public List<CallLog> getCallLogList2(HashMap<String, String> hashMap){
        String sql = "select * from (SELECT t3.id_date_contact, t3.id_date_dimension, t3.id_contact, t3.call_sum, t3.call_duration_sum , t3.telephone, t3.name, t4.year, t4.month, t4.day FROM (SELECT t2.id_date_contact, t2.id_date_dimension, t2.id_contact, t2.call_sum, t2.call_duration_sum , t1.telephone, t1.name FROM (SELECT id, telephone, name FROM tb_contacts WHERE telephone = :telephone ) t1 INNER JOIN tb_call t2 ON t1.id = t2.id_contact ) t3 INNER JOIN (SELECT id, year, month, day FROM tb_dimension_date WHERE year = :year AND month= :month) t4 ON t3.id_date_dimension = t4.id ORDER BY t4.year, t4.month, t4.day)t5 where t5.day !=-1;";
        BeanPropertyRowMapper<CallLog> contactBeanPropertyRowMapper = new BeanPropertyRowMapper<>(CallLog.class);
        List<CallLog> contactList = namedParameterJdbcTemplate.query(sql, hashMap, contactBeanPropertyRowMapper);
        return contactList;
    }

    public List<QueryList> getCallLogList3(){
        String sql = " SELECT * from tb_contacts,(SELECT id_contact,time from (SELECT id_contact,SUM(call_duration_sum) time from  tb_call GROUP BY id_contact ORDER BY time DESC) t1 LIMIT 0,10) t2 WHERE t2.id_contact=tb_contacts.id;";
        BeanPropertyRowMapper<QueryList> queryListBeanPropertyRowMapper = new BeanPropertyRowMapper<>(QueryList.class);
        List<QueryList> queryLists = namedParameterJdbcTemplate.query(sql, queryListBeanPropertyRowMapper);
        return queryLists;
    }

}
