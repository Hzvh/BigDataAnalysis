package com.hzvh.kv.key;

import com.hzvh.kv.base.BaseDimension;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ContactDimension extends BaseDimension {
    private String name;
    private String phoneNum;

    public ContactDimension() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return name + "\t" + phoneNum;
    }

    @Override
    public int compareTo(BaseDimension o) {
        ContactDimension other = (ContactDimension)o;
        return phoneNum.compareTo(other.phoneNum);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(name);
        dataOutput.writeUTF(phoneNum);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.name = dataInput.readUTF();
        this.phoneNum = dataInput.readUTF();
    }
}
