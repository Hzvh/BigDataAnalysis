package com.hzvh.dao;

import com.hzvh.constants.Constant;
import com.hzvh.utils.ConnectionInstance;
import com.hzvh.utils.HbaseUtil;
import com.hzvh.utils.PropertiesUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HbaseDAO {
    private String namespace;
    private String tableName;
    private int regions;
    private String cf;
    private SimpleDateFormat sdf =null;

    private HTable table;
    private String flag;
    private List<Put> listPut;

    public HbaseDAO() throws IOException {
        namespace= PropertiesUtil.properties.getProperty("hbase.namespace");
        tableName= PropertiesUtil.properties.getProperty("hbase.table.name");
        regions= Integer.valueOf(PropertiesUtil.properties.getProperty("hbase.regions"));
        cf=PropertiesUtil.properties.getProperty("hbase.table.cf");
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        listPut = new ArrayList<>();
        flag ="1";

        if (!HbaseUtil.isTableExist(tableName)){
            HbaseUtil.initNamespace(namespace);
            HbaseUtil.createTable(tableName,regions,cf,"f2");
        }

    }
    public void put(String ori) throws IOException, ParseException {

        if (listPut.size()==0){
            Connection connection = ConnectionInstance.getInstance();
            table = (HTable) connection.getTable(TableName.valueOf(tableName));
            table.setAutoFlushTo(false);

            table.setWriteBufferSize(1024*1024);
        }


        if (ori==null)return;
        String [] split = ori.split(",");

        String caller = split[0];
        String callee = split[1];
        String buildTime = split[2];
        long time = sdf.parse(buildTime).getTime();
        String builedtime_ts = time+"";
        String duration = split[3];

        String regionHash = HbaseUtil.getRegionHash(caller,buildTime,regions);

        String rowKey = HbaseUtil.getRowKey(regionHash, caller, buildTime, callee, flag, duration);


        Put put = new Put(Bytes.toBytes(rowKey));

        put.addColumn(Bytes.toBytes(cf),Bytes.toBytes("call1"),Bytes.toBytes(caller));
        put.addColumn(Bytes.toBytes(cf),Bytes.toBytes("buildtime"),Bytes.toBytes(buildTime));
        put.addColumn(Bytes.toBytes(cf),Bytes.toBytes("buildtime_ts"),Bytes.toBytes(builedtime_ts));
        put.addColumn(Bytes.toBytes(cf),Bytes.toBytes("call2"),Bytes.toBytes(callee));
        put.addColumn(Bytes.toBytes(cf),Bytes.toBytes("flag"),Bytes.toBytes(flag));
        put.addColumn(Bytes.toBytes(cf),Bytes.toBytes("duration"),Bytes.toBytes(duration));

        listPut.add(put);
        if (listPut.size()>20){
            table.put(listPut);
            table.flushCommits();
            listPut.clear();
            table.close();
        }


    }

}
