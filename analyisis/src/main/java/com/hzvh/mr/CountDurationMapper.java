package com.hzvh.mr;

import com.hzvh.kv.key.CommDimension;
import com.hzvh.kv.key.ContactDimension;
import com.hzvh.kv.key.DateDimension;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CountDurationMapper extends TableMapper<CommDimension, Text> {
    Map<String,String> phoneName = new HashMap<>();
    private Text v = new Text();
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        init();
    }
    private void init(){
        phoneName.put("16982272415","周伟业");
        phoneName.put("18181090118","林子明");
        phoneName.put("14472162346","庄静丽");
        phoneName.put("17544587543","陈小婷");
        phoneName.put("15180510311","张杨平");
        phoneName.put("14303309467","武冠轩");
        phoneName.put("17988743883","周泉江");
        phoneName.put("19038657029","余炜鹏");
        phoneName.put("16341346919","张灿云");
        phoneName.put("14498330765","许伟谊");
        phoneName.put("17982867717","郭锦贤");
        phoneName.put("16580411780","朱泽亮");
        phoneName.put("17862324938","吴羽静");
        phoneName.put("13670511962","张铉");
        phoneName.put("19810485336","蔡迪全");
        phoneName.put("19861749596","林炎秀");
        phoneName.put("17178406025","梁梦怡");
        phoneName.put("17777543404","刁慧彬");
        phoneName.put("17228368702","陈增宏");
        phoneName.put("15418089835","刘瑞雄");
        phoneName.put("15882677735","刘俊声");
        phoneName.put("18032128470","陈学斌");
        phoneName.put("14365371383","王纯淇");
        phoneName.put("17955449799","梁绍荣");
        phoneName.put("19148700280","梁成鑫");
        phoneName.put("17789346842","廖庭康");
        phoneName.put("16886343317","张华扬");
        phoneName.put("13176592102","熊欢");
        phoneName.put("13786415445","马晓华");
        phoneName.put("18429985219","梁夏天");
        phoneName.put("14247481144","叶学斌");
        phoneName.put("17178423167","麦亮森");
        phoneName.put("17977284085","陈文创");
        phoneName.put("19671856969","苏日锐");
        phoneName.put("19550957182","庄海富");
        phoneName.put("14010454642","黄宇康");
        phoneName.put("16572503555","张铭");
        phoneName.put("18965445314","杨晴晴");
        phoneName.put("15403497483","姚能燕");
        phoneName.put("13873948444","陈培凤");
        phoneName.put("16670628342","杜国仲");
        phoneName.put("16273953042","黄俊轩");
        phoneName.put("18693022295","邓伟键");
        phoneName.put("19507075134","叶钦帅");
        phoneName.put("19580279887","何俊霖");
        phoneName.put("18086831149","文嘉星");
        phoneName.put("18137999087","王程锋");
        phoneName.put("14064709393","邹子庆");
        phoneName.put("16683428722","邓校君");
        phoneName.put("16199098354","杨君胜");
        phoneName.put("13394569801","陈煜");
        phoneName.put("18057208490","罗锦坚");
        phoneName.put("19759415495","黄锦晖");
        phoneName.put("14155859241","吴文悦");
        phoneName.put("18443649338","李伟豪");
        phoneName.put("19130682505","李亮蓉");
        phoneName.put("17594357827","邱泽松");
        phoneName.put("14312115187","杨建民");
        phoneName.put("15255192730","杨泳志");
    }

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
        //设置value的值
        v.set(duration);

        CommDimension commDimension = new CommDimension();
        ContactDimension contactDimension = new ContactDimension();
        //第一个联系人维度封装
        contactDimension.setName(phoneName.get(call1));
        contactDimension.setPhoneNum(call1);
        //年维度
        DateDimension yearDimension = new DateDimension(year,"-1","-1");
        commDimension.setContactDimension(contactDimension);
        commDimension.setDateDimension(yearDimension);
        context.write(commDimension,v);
        //月维度
        DateDimension monthDimension = new DateDimension(year,month,"-1");
        commDimension.setDateDimension(monthDimension);
        context.write(commDimension,v);

        //天维度
        DateDimension dayDimension = new DateDimension(year,month,day);
        commDimension.setDateDimension(dayDimension);
        context.write(commDimension,v);
        //第二个联系人维度封装
        contactDimension.setName(phoneName.get(call2));
        contactDimension.setPhoneNum(call2);

        //年维度
        commDimension.setContactDimension(contactDimension);
        commDimension.setDateDimension(yearDimension);
        context.write(commDimension,v);
        //月维度
        commDimension.setDateDimension(monthDimension);
        context.write(commDimension,v);
        //天维度
        commDimension.setDateDimension(dayDimension);
        context.write(commDimension,v);
    }
}
