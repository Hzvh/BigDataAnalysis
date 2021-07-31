package com.hzvh.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HbaseScanUtil {

    private List<String[]> list;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    private  int i = 0;
    public void  init(String phone,String start,String stop) throws ParseException {

        list = new ArrayList<>();
        Date startDate = sdf.parse(start);
        Date stopDate = sdf.parse(stop);


        Calendar startPoint = Calendar.getInstance();
        startPoint.setTime(startDate);


        Calendar stopPoint = Calendar.getInstance();
        stopPoint.setTime(stopDate);
        stopPoint.add(Calendar.MONTH,1);
        while (stopPoint.getTimeInMillis()<=stopDate.getTime()){

            String startTime = sdf.format(startPoint.getTime());
            String stopTime = sdf.format(startPoint.getTime());

            String regionHash = HbaseUtil.getRegionHash(phone, startTime, 6);
            String startRow = regionHash + "_" + phone +"_"+startTime;
            String stopRow = regionHash + "_" + phone +"_"+stopTime;

            String[] rowkeys = {startRow,stopRow};
            list.add(rowkeys);

            startPoint.add(Calendar.MONTH,1);
            stopPoint.add(Calendar.MONTH,1);

        }

    }


    public boolean hasNext(){
        return  i<list.size();
    }
    public String[] next(){
        return list.get(i++);
    }
}
