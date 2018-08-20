package service;

import dao.RecordDAO;
import entity.Record;
import util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportService {
    //获取某一天的消费金额
    public int getDaySpend(Date d, List<Record> monthRawData){
        int daySpend=0;
        for (Record record:monthRawData){
            if (record.date.equals(d))
                daySpend+=record.getSpend();
        }
        return daySpend;
    }

    //获取一个月的消费记录集合
    public List<Record> listThisMonthRecords(){
        RecordDAO dao=new RecordDAO();
        List<Record> monthRawData=dao.listThisMonth();
        List<Record> result=new ArrayList<>();

        Date monthBegin=DateUtil.monthBegin();
        Calendar c=Calendar.getInstance();
        int monthTotalDay=DateUtil.thisMonthTotalDay();
        for (int i=0;i<monthTotalDay;i++){
            Record r=new Record();

            c.setTime(monthBegin);
            c.add(Calendar.DATE,i);
            int daySpend=getDaySpend(c.getTime(),monthRawData);
            r.spend=daySpend;
            result.add(r);
        }
        return result;
    }
}
