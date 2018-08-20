package gui.panel;

import entity.Category;
import entity.Record;
import gui.listener.DetailListener;
import gui.model.CategoryComboBoxModel;
import gui.model.DetailTableModel;
import service.DetailService;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class DetailPanel extends WorkingPanel {
    //设置皮肤
    static {
        GUIUtil.useLNF();
    }

    //单例模式
    public static DetailPanel instance=new DetailPanel();

    public CategoryComboBoxModel cbModel=new CategoryComboBoxModel();
    public JComboBox<Category> cbCategory=new JComboBox<>(cbModel);
    public JButton bAll=new JButton("全部");
    public JButton bSearch=new JButton("查询");
    public JButton bDelete=new JButton("删除");
    public JButton bEdit=new JButton("编辑");

    public DetailTableModel dtm=new DetailTableModel();
    public JTable t=new JTable(dtm);

    public DetailPanel(){
        this.setLayout(new BorderLayout());

        JScrollPane sp=new JScrollPane(t);

        this.add(north(),BorderLayout.NORTH);
        this.add(sp,BorderLayout.CENTER);
        this.add(south(),BorderLayout.SOUTH);

//        updateData();
        addListener();
    }

    private JPanel north(){
        JPanel p=new JPanel();
        p.setLayout(new FlowLayout());
        p.add(bAll);
        p.add(cbCategory);
        p.add(bSearch);

        return p;
    }

    private JPanel south(){
        JPanel p=new JPanel();
        p.setLayout(new FlowLayout());
        p.add(bDelete);
        p.add(bEdit);

        return p;
    }

    public Record getSelectedRecord(){
        int index=t.getSelectedRow();
        return dtm.rs.get(index);
    }

    public Category getSelectedCategory(){
        return (Category) cbCategory.getSelectedItem();
    }

    @Override
    public void addListener() {
        DetailListener listener=new DetailListener();
        bAll.addActionListener(listener);
        bSearch.addActionListener(listener);
        bDelete.addActionListener(listener);
        bEdit.addActionListener(listener);
    }

    @Override
    public void updateData() {
        dtm.rs=new DetailService().list();
        t.updateUI();
        t.getSelectionModel().setSelectionInterval(0,0);

        if (dtm.rs.size()==0){
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
        }
        if (dtm.rs.size()!=0){
            bEdit.setEnabled(true);
            bDelete.setEnabled(true);
        }
    }
}
