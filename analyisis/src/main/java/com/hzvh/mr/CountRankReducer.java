package com.hzvh.mr;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CountRankReducer extends Reducer<Text, Text,Text, Text> {
    Text t = new Text();
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {
        int countSum = 0;//通话总次数
        int durationSum = 0;//通话总时长
        for (Text value : values) {
            countSum++;
            durationSum += Integer.valueOf(value.toString());

        }
        String sum = countSum + "_" + durationSum;
        t.set(sum);

        context.write(key,t);
    }
}
