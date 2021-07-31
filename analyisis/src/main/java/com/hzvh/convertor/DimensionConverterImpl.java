
package com.hzvh.convertor;

import com.hzvh.kv.base.BaseDimension;

import com.hzvh.kv.key.ContactDimension;
import com.hzvh.kv.key.DateDimension;

import com.hzvh.util.JDBCInstance;
import com.hzvh.util.JDBCUtil;
import com.hzvh.util.LRUCache;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DimensionConverterImpl implements IConvertor {


    //创建数据缓存队列
    LRUCache lruCache = new LRUCache(3000);


    public int getDimensionID(BaseDimension baseDimension){
        //1、根据传入的维度对象取得该维度对象对应的cachekey
        //判断缓存中是否存在该cacheKey的缓存
        String cacheKey = getCacheKey(baseDimension);

        if (lruCache.containsKey(cacheKey)) {
            return lruCache.get(cacheKey);
        }

        //2.
        String[] sqls = getSqls(baseDimension);


        Connection connection =null;
        try{
             connection = JDBCInstance.getInstance();
        }catch (SQLException e){
            e.printStackTrace();
        }


        //3.
        int id=-1;
        try{
            id = execSql(sqls,connection,baseDimension);
        }catch (SQLException e){
            e.printStackTrace();
        }

        if(id == -1) throw  new RuntimeException("dont match dimension");


        //
        lruCache.put(cacheKey,id);
        return id;
    }
    private int execSql(String[] sqls, Connection connection, BaseDimension baseDimension)
            throws SQLException {
        int id =-1;
        PreparedStatement preparedStatement = null;
        //ResultSet resultSet = null;
        try{
            preparedStatement = connection.prepareStatement(sqls[0]);
        }catch (SQLException e){
            e.printStackTrace();
        }

        //第一次查询
        setArguments(preparedStatement,baseDimension);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        //查不到，插 if (resultSet.next()) {
        //            return resultSet.getInt(1);
        //        }入
        preparedStatement = connection.prepareStatement(sqls[1]);
        setArguments(preparedStatement,baseDimension);
        preparedStatement.executeUpdate();

        //第二次查询
        preparedStatement = connection.prepareStatement(sqls[0]);
        setArguments(preparedStatement,baseDimension);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }

        return id;
    }
    private void setArguments(PreparedStatement preparedStatement, BaseDimension baseDimension) throws SQLException {
        int i=0;
        if (baseDimension instanceof ContactDimension) {
            ContactDimension contactDimension = (ContactDimension)baseDimension;
            preparedStatement.setString(++i,contactDimension.getPhoneNum());
            preparedStatement.setString(++i,contactDimension.getName());

        } else{
            DateDimension dateDimension = (DateDimension) baseDimension;
            preparedStatement.setInt(++i,Integer.valueOf(dateDimension.getYear()));
            preparedStatement.setInt(++i,Integer.valueOf(dateDimension.getMonth()));
            preparedStatement.setInt(++i,Integer.valueOf(dateDimension.getDay()));
        }
    }


    private String[] getSqls (BaseDimension baseDimensionn){
        String[] sqls = new String[2];
        if (baseDimensionn instanceof ContactDimension){
           // ContactDimension contactDimension = (ContactDimension) baseDimensionn;
            //contactDimension.getPhoneNum();
            sqls[0] = "SELECT `id` FROM `tb_contacts` WHERE `telephone` = ? AND `name` = ?";
            sqls[1] = "INSERT INTO tb_contacts VALUES(NULL,?,?);";
        }else if (baseDimensionn instanceof DateDimension){
            sqls[0] = "SELECT `id` FROM `tb_dimension_date` WHERE `year` = ? AND `month` = ? AND `day` = ?";
            sqls[1] = "INSERT INTO tb_dimension_date VALUES(NULL,?,?,?);";
        }
        return sqls;
    }



    private String getCacheKey(BaseDimension baseDimension) {
            StringBuffer sb = new StringBuffer();
            if (baseDimension instanceof ContactDimension) {
                ContactDimension contactDimension = (ContactDimension)baseDimension;
                sb.append(contactDimension.getPhoneNum());
                /*if (lruCache.containsKey(contactDimension.getPhoneNum())) {
                    lruCache.get(contactDimension.getPhoneNum());
                }*/
            } else if (baseDimension instanceof DateDimension) {
                DateDimension dateDimension = (DateDimension)baseDimension;
                sb.append(dateDimension.getYear()).append(dateDimension.getMonth())
                        .append(dateDimension.getDay());

        }
        return sb.toString();
    }
}
