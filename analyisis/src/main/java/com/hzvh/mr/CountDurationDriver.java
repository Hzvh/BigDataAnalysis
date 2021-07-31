package com.hzvh.mr;

import com.hzvh.kv.key.CommDimension;
import com.hzvh.kv.value.CountDurationValue;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

public class CountDurationDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = HBaseConfiguration.create();
        Job job = Job.getInstance(configuration);
        job.setJarByClass(CountDurationDriver.class);

        Scan scan = new Scan();

        TableMapReduceUtil.initTableMapperJob("ct:calllog",scan,CountDurationMapper.class, CommDimension.class, Text.class,job);

        job.setReducerClass(CountDurationReducer.class);
//        job.setOutputKeyClass(CommDimension.class);
//        job.setOutputValueClass(CountDurationValue.class);

        job.setOutputFormatClass(MySQLOutputFormat.class);

        boolean result =  job.waitForCompletion(true) ;
        System.exit(result ? 0 : 1);
    }
}
