package service;

import dao.RecordDAO;
import entity.Record;

import java.util.Date;
import java.util.List;

public class DetailService {
    RecordDAO recordDAO=new RecordDAO();

    public List<Record> list(){
        List<Record> cs=recordDAO.list();
        return cs;
    }

    //外键查询
    public List<Record> list(int cid){
        return recordDAO.list(cid);
    }

    //更新
    public void update(int spend,int id,int cid,String comment,Date date){
        Record record=new Record();
        record.id=id;
        record.cid=cid;
        record.date=date;
        record.spend=spend;
        record.comment=comment;

        recordDAO.update(record);
    }

    //删除
    public void delete(int id){
        recordDAO.delete(id);
    }
}
