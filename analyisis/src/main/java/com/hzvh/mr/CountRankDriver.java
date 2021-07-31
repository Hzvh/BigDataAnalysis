package com.hzvh.mr;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

public class CountRankDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = HBaseConfiguration.create();
        Job job = Job.getInstance(configuration);
        job.setJarByClass(CountRankDriver.class);

        Scan scan = new Scan();

        TableMapReduceUtil.initTableMapperJob("ct:calllog",scan,CountRankMapper.class, Text.class, Text.class,job);

        job.setReducerClass(CountRankReducer.class);


        job.setOutputFormatClass(MySqllOutputFormat.class);

        boolean result =  job.waitForCompletion(true) ;
        System.exit(result ? 0 : 1);
    }
}
