package com.hzvh.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.text.DecimalFormat;

public class HbaseUtil {

    private static Configuration conf= HBaseConfiguration.create();

    public HbaseUtil() throws IOException {
    }

    public static boolean isTableExist(String tableName) throws IOException {
        Connection connection = ConnectionFactory.createConnection(conf);

        Admin admin = connection.getAdmin();
        boolean result = admin.tableExists(TableName.valueOf(tableName));

        close(connection,admin);
        return result;
    }

    public static void  initNamespace(String namespace) throws IOException {
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();


        NamespaceDescriptor descriptor = NamespaceDescriptor.create(namespace)
                .addConfiguration("create_ts",String.valueOf(System.currentTimeMillis()))
                .build();

        admin.createNamespace(descriptor);

        close(connection,admin);
    }


    public static String getRowKey(String regionHash,
                                   String caller,
                                   String buildTime,
                                   String callee,
                                   String flag,
                                   String duration){
        return regionHash+"_"+caller+"_"+buildTime+"_"+callee+"_"+flag+"_"+duration;
    }



    public static  byte[][] getSplitKeys(int regions){

        DecimalFormat df = new DecimalFormat("00");
        byte[][] splitKeys = new byte[regions][];
        for (int i = 0; i < regions; i++) {
            splitKeys[i]= Bytes.toBytes(df.format(i)+"|");
        }
        for (byte[] splitKey : splitKeys) {
            System.out.println(Bytes.toString(splitKey));
        }

        return splitKeys;
    }
    public static  String getRegionHash(String caller,String buildTime,int regions){
        int len = caller.length();
        String last4Num = caller.substring(len-4);
        String yearMonth = buildTime.replaceAll("-","").substring(0,6);
        int regionCode = (Integer.valueOf(last4Num)^ Integer.valueOf(yearMonth))%regions;

        DecimalFormat df = new DecimalFormat("00");

        return df.format(regionCode);
    }

    public static  void createTable(String tableName,int regions,String... columnFamily) throws IOException {

        if (isTableExist(tableName)) {
            System.out.println("表" + tableName + "已存在");
            return;
        }
        Connection connection = ConnectionFactory.createConnection(conf);

        Admin admin = connection.getAdmin();
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));

        for (String cf : columnFamily) {
            hTableDescriptor.addFamily(new HColumnDescriptor(cf));
        }

        hTableDescriptor.addCoprocessor("com.hzvh.coprocessor.CalleeWriteObserver");

        admin.createTable(hTableDescriptor,getSplitKeys(regions));

    }
    private  static void  close(Connection connection, Admin admin, Table... tables){
        if (connection!=null){
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (admin!=null){
            try {
                admin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (tables.length<=0)
            return;

        for (Table table : tables) {
            if (table!=null){
                try {
                    table.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//    public static void main(String[] args) {
//        getSplitKeys(6);
//        System.out.println(getRegionHash("18181090118", "2019-02-21 13:13:13",
//                6));
//        System.out.println(getRowKey(getRegionHash("18181090118", "2019-02-21 13:13:13", 6),
//                "18181090118", "2019-02-21 13:13:13", "16982272415",
//                "0180"));
//    }
}
