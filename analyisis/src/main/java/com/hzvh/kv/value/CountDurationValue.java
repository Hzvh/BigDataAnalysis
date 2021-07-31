package com.hzvh.kv.value;

import com.hzvh.kv.base.BaseValue;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class CountDurationValue extends BaseValue {
    private String countSum;
    private String durationSum;

    public CountDurationValue() {
    }

    public CountDurationValue(String countSum, String durationSum) {
        this.countSum = countSum;
        this.durationSum = durationSum;
    }

    public String getCountSum() {
        return countSum;
    }

    public void setCountSum(String countSum) {
        this.countSum = countSum;
    }

    public String getDurationSum() {
        return durationSum;
    }

    public void setDurationSum(String durationSum) {
        this.durationSum = durationSum;
    }

    @Override
    public String toString() {
        return countSum + "\t" + durationSum;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(countSum);
        dataOutput.writeUTF(durationSum);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.countSum = dataInput.readUTF();
        this.durationSum = dataInput.readUTF();
    }
}
