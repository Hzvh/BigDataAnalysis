package controller;


import bean.*;
import dao.CallLogDAO;
import dao.ContactDAO;
import dao.IntimacyDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
//import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;

@Controller
public class CallLogHandler {
    @RequestMapping("/querylist")
    public String querylist(Model model) {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//        CallLogDAO callLogDAO = applicationContext.getBean(CallLogDAO.class);
//        List<QueryList> callLogList3 = callLogDAO.getCallLogList3();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("jsp/querylist");
//        modelAndView.addObject("QueryLists", callLogList3);
//        System.out.println(modelAndView);
//        return modelAndView;
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        CallLogDAO callLogDAO = applicationContext.getBean(CallLogDAO.class);
        List<QueryList> callLogList3 = callLogDAO.getCallLogList3();

        if (callLogList3.isEmpty()) {
            System.out.println("Error");
            return "/jsp/Error";

        }

        System.out.println(callLogList3);
        StringBuilder dateString = new StringBuilder();
        StringBuilder durationString = new StringBuilder();

        for (int i = 0; i < callLogList3.size(); i++) {
            QueryList queryList = callLogList3.get(i);
            if (Integer.valueOf(queryList.getId()) > 0) {
                dateString.append(queryList.getName()).append(" ").append(",");
                durationString.append(Float.valueOf(queryList.getTime()) / 60f).append(",");
            }
        }
        //1月,2月,3月,4月,5月,6月,7月,8月,9月,10月,11月,12月,
        model.addAttribute("date", dateString.deleteCharAt(dateString.length() - 1));
        model.addAttribute("duration", durationString.deleteCharAt(durationString.length() - 1));
        return "jsp/querylist";
    }


    @RequestMapping("/queryIntimacy")
    public String queryCallLog(Model model, QueryInfoInti queryInfoInti) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        IntimacyDao intimacyDao = applicationContext.getBean(IntimacyDao.class);

        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("telephone", String.valueOf(queryInfoInti.getTelephone()));
        System.out.println(paramMap);
        List<Intimacy> intimacyList = intimacyDao.getIntimacy(paramMap);

        if (intimacyList.isEmpty()) {
            System.out.println("Error");
            return "/jsp/Error2";

        }
        System.out.println(intimacyList);

        StringBuilder dateString = new StringBuilder();
        StringBuilder countString = new StringBuilder();
        StringBuilder durationString = new StringBuilder();
        StringBuilder RankString = new StringBuilder();

        for (int i = 0; i < intimacyList.size(); i++) {
            Intimacy intimacy = intimacyList.get(i);
            if (Integer.valueOf(intimacy.getContact_id2()) > 0) {
                dateString.append(intimacy.getName2()).append(" ").append(",");
                countString.append(intimacy.getCall_count()).append(",");
                RankString.append(intimacy.getIntimacy_rank()).append(",");
                durationString.append(Float.valueOf(intimacy.getCall_duration_count()) / 60f).append(",");
            }
        }
        model.addAttribute("list",intimacyList);
        model.addAttribute("telephone", String.valueOf(queryInfoInti.getTelephone()));
        model.addAttribute("id", intimacyList.get(0).getContact_id1());
        model.addAttribute("name1", intimacyList.get(0).getName1());
        model.addAttribute("date", dateString.deleteCharAt(dateString.length() - 1));
        model.addAttribute("count", countString.deleteCharAt(countString.length() - 1));
        model.addAttribute("rank", RankString.deleteCharAt(RankString.length() - 1));
        model.addAttribute("duration", durationString.deleteCharAt(durationString.length() - 1));
        return "/jsp/IntimacyListEchart";
    }


    @RequestMapping("/queryCallLogList1")
    public String queryCallLog1(Model model, QueryInfo queryInfo) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        CallLogDAO callLogDAO = applicationContext.getBean(CallLogDAO.class);

        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("telephone", String.valueOf(queryInfo.getTelephone()));
        paramMap.put("year", String.valueOf(queryInfo.getYear()));
        paramMap.put("month", String.valueOf(queryInfo.getMonth()));

        List<CallLog> callLogList = callLogDAO.getCallLogList2(paramMap);

        if (callLogList.isEmpty()) {
            System.out.println("Error");
            return "/jsp/Error1";

        }
        System.out.println(paramMap);
        System.out.println(callLogList);
        StringBuilder dateString = new StringBuilder();
        StringBuilder countString = new StringBuilder();
        StringBuilder durationString = new StringBuilder();

        for (int i = 0; i < callLogList.size(); i++) {
            CallLog callLog = callLogList.get(i);
            if (Integer.valueOf(callLog.getDay()) > -2) {
                dateString.append(callLog.getDay()).append("日").append(",");
                countString.append(callLog.getCall_sum()).append(",");
                durationString.append(Float.valueOf(callLog.getCall_duration_sum()) / 60f).append(",");
            }
        }
        //1月,2月,3月,4月,5月,6月,7月,8月,9月,10月,11月,12月,
        model.addAttribute("telephone", callLogList.get(0).getTelephone());
        model.addAttribute("name", callLogList.get(0).getName());
        model.addAttribute("date", dateString.deleteCharAt(dateString.length() - 1));
        model.addAttribute("count", countString.deleteCharAt(countString.length() - 1));
        model.addAttribute("duration", durationString.deleteCharAt(durationString.length() - 1));
        return "/jsp/CallLogListEchart1";
    }


    @RequestMapping("/CallLogListEchart")
    public String queryCallLog2(Model model, QueryInfo queryInfo) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        CallLogDAO callLogDAO = applicationContext.getBean(CallLogDAO.class);

        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("telephone", String.valueOf(queryInfo.getTelephone()));
        paramMap.put("year", String.valueOf(queryInfo.getYear()));
        //paramMap.put("day", String.valueOf(queryInfo.getDay()));

        List<CallLog> callLogList = callLogDAO.getCallLogList1(paramMap);

        if (callLogList.isEmpty()) {
            System.out.println("Error");
            return "/jsp/Error";

        }
            System.out.println(paramMap);
            System.out.println(callLogList);
            StringBuilder dateString = new StringBuilder();
            StringBuilder countString = new StringBuilder();
            StringBuilder durationString = new StringBuilder();

            for (int i = 0; i < callLogList.size(); i++) {
                CallLog callLog = callLogList.get(i);
                if (Integer.valueOf(callLog.getMonth()) > 0) {
                    dateString.append(callLog.getMonth()).append("月").append(",");
                    countString.append(callLog.getCall_sum()).append(",");
                    durationString.append(Float.valueOf(callLog.getCall_duration_sum()) / 60f).append(",");
                }
            }
            //1月,2月,3月,4月,5月,6月,7月,8月,9月,10月,11月,12月,
            model.addAttribute("telephone", callLogList.get(0).getTelephone());
            model.addAttribute("name", callLogList.get(0).getName());
            model.addAttribute("date", dateString.deleteCharAt(dateString.length() - 1));
            model.addAttribute("count", countString.deleteCharAt(countString.length() - 1));
            model.addAttribute("duration", durationString.deleteCharAt(durationString.length() - 1));
            return "/jsp/CallLogListEchart";
        }

}
