package com.hzvh;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 生产数据
 */
public class Producer {

    private String start="2019-01-01";
    private String end="2020-01-01";


    ArrayList<String> phoneNum = new ArrayList<>();

    Map<String,String> phoneName = new HashMap<>();

    public void init(){
        phoneNum.add("16982272415");
        phoneNum.add("18181090118");
        phoneNum.add("14472162346");
        phoneNum.add("17544587543");
        phoneNum.add("15180510311");
        phoneNum.add("14303309467");
        phoneNum.add("17988743883");
        phoneNum.add("19038657029");
        phoneNum.add("16341346919");
        phoneNum.add("14498330765");
        phoneNum.add("17982867717");
        phoneNum.add("16580411780");
        phoneNum.add("17862324938");
        phoneNum.add("13670511962");
        phoneNum.add("19810485336");
        phoneNum.add("19861749596");
        phoneNum.add("17178406025");
        phoneNum.add("17777543404");
        phoneNum.add("17228368702");
        phoneNum.add("15418089835");
        phoneNum.add("15882677735");
        phoneNum.add("18032128470");
        phoneNum.add("14365371383");
        phoneNum.add("17955449799");
        phoneNum.add("19148700280");
        phoneNum.add("17789346842");
        phoneNum.add("16886343317");
        phoneNum.add("13176592102");
        phoneNum.add("13786415445");
        phoneNum.add("18429985219");
        phoneNum.add("14247481144");
        phoneNum.add("17178423167");
        phoneNum.add("17977284085");
        phoneNum.add("19671856969");
        phoneNum.add("19550957182");
        phoneNum.add("14010454642");
        phoneNum.add("16572503555");
        phoneNum.add("18965445314");
        phoneNum.add("15403497483");
        phoneNum.add("13873948444");
        phoneNum.add("16670628342");
        phoneNum.add("16273953042");
        phoneNum.add("18693022295");
        phoneNum.add("19507075134");
        phoneNum.add("19580279887");
        phoneNum.add("18086831149");
        phoneNum.add("18137999087");
        phoneNum.add("14064709393");
        phoneNum.add("16683428722");
        phoneNum.add("16199098354");
        phoneNum.add("13394569801");
        phoneNum.add("18057208490");
        phoneNum.add("19759415495");
        phoneNum.add("14155859241");
        phoneNum.add("18443649338");
        phoneNum.add("19130682505");
        phoneNum.add("17594357827");
        phoneNum.add("14312115187");
        phoneNum.add("15255192730");

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

    //caller callername callee calleename build_time build_time_ts duration
    public String productLog() throws ParseException {
        String caller;
        String callee;
        String buildTime;
        int dura;
        //1.随机生产两个不同的电话号码
        int callerIndex = (int) (Math.random() * phoneNum.size());
        caller = phoneNum.get(callerIndex);

        while(true){
            int calleeIndex = (int) (Math.random() * phoneNum.size());
            callee = phoneNum.get(calleeIndex);
            if (callerIndex !=calleeIndex) break;
        }


        //2.随机生成通话建立时间（start，end）
        buildTime = randomBuildTime(start,end);


        //3.随机生成通话时长
        dura = (int)(Math.random() * 30*60)+1;
        DecimalFormat df = new DecimalFormat("0000");
        String duration = df.format(dura);


        return caller+","+callee+","+buildTime+","+duration+"\n";

    }

    //随机生成通话建立时间
    private String randomBuildTime(String start,String end) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long startPoint = sdf.parse(start).getTime();
        long endPoint = sdf.parse(end).getTime();

        long resultTS = startPoint+(long)(Math.random()*(endPoint-startPoint));

        return sdf2.format(new Date(resultTS));
    }

    public void writeLog(String path) throws IOException, ParseException, InterruptedException {
        FileOutputStream fos = new FileOutputStream(path);
        OutputStreamWriter osw = new OutputStreamWriter(fos);

        while(true){

            String log = productLog();
            System.out.print(log);
            osw.write(log);
            osw.flush();
            Thread.sleep(300);
        }

    }

    public static void main(String[] args) throws ParseException, InterruptedException, IOException {
//        DecimalFormat df = new DecimalFormat("0000");
//        String format = df.format(12);
//        System.out.println(format);
        if(args.length<=0){
            System.out.println("没有参数");
            System.exit(0);
        }
        Producer producer = new Producer();
        producer.init();
        producer.writeLog(args[0]);
    }
}
