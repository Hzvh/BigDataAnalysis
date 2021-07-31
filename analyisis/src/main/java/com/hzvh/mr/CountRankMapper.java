package com.hzvh.mr;


import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;

import java.io.IOException;


public class CountRankMapper extends TableMapper<Text, Text> {

    private Text v = new Text();
    private Text k = new Text();



    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {
        String rowkey = Bytes.toString(value.getRow());
        String[] split = rowkey.split("_");

        String flag = split[4];
        if ("0".equals(flag)){
            return;
        }
        String call1 = split[1];
        String call2 = split[3];
        String buildTime = split[2];
        String year = buildTime.substring(0, 4);
        String month = buildTime.substring(5, 7);
        String day = buildTime.substring(8, 10);

        String duration = split[5];
        String call = call1 + "_" + call2;
        //设置value的值
        k.set(call);
        v.set(duration);


        context.write(k,v);

    }
}
