package com.hzvh.mr;

import com.hzvh.kv.key.CommDimension;
import com.hzvh.kv.value.CountDurationValue;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CountDurationReducer extends Reducer<CommDimension, Text,CommDimension, CountDurationValue> {
    private CountDurationValue v = new CountDurationValue();
    @Override
    protected void reduce(CommDimension key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {
        int countSum = 0;//通话总次数
        int durationSum = 0;//通话总时长
        for (Text value : values) {
            countSum++;
            durationSum += Integer.valueOf(value.toString());

        }
        v.setCountSum(countSum + "");
        v.setDurationSum(durationSum + "");
        context.write(key,v);
    }
}
