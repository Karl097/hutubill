package gui.model;

import entity.Category;
import entity.Record;
import service.CategoryService;
import service.DetailService;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

public class DetailTableModel implements TableModel {
    String [] columnNames=new String[]{"金额","分类","备注","日期"};
    public List<Record> rs =new DetailService().list();

    @Override
    public int getRowCount() {
        return rs.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Record r= rs.get(rowIndex);

        if (columnIndex==0)
            return r.spend;
        if (columnIndex==1)
            return new CategoryService().getName(r.cid);
        if (columnIndex==2)
            return r.comment;
        if (columnIndex==3)
            return r.date;
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
