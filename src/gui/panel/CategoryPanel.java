package gui.panel;

import entity.Category;
import gui.listener.CategoryListener;
import gui.model.CategoryTableModel;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class CategoryPanel extends WorkingPanel{
    //设置皮肤
    static {
        GUIUtil.useLNF();
    }

    //单例模式
    public static CategoryPanel instance=new CategoryPanel();

    //组件
    public JButton bAdd=new JButton("新增");
    public JButton bEdit=new JButton("编辑");
    public JButton bDelete=new JButton("删除");

    public CategoryTableModel ctm=new CategoryTableModel();
    public JTable t=new JTable(ctm);

    //主面板
    public CategoryPanel(){
        GUIUtil.setColor(ColorUtil.blueColor,bAdd,bEdit,bDelete);

        this.setLayout(new BorderLayout());
        this.add(sp(),BorderLayout.CENTER);
        this.add(pSubmit(),BorderLayout.SOUTH);

        addListener();
        updateData();
    }

    //图表面板
    private JScrollPane sp(){
        JScrollPane sp=new JScrollPane(t);

        return sp;
    }

    //按钮面板
    private JPanel pSubmit(){
        JPanel p=new JPanel();

        p.setLayout(new FlowLayout());

        p.add(bAdd);
        p.add(bEdit);
        p.add(bDelete);

        return p;
    }

    public Category getSelectedCategory(){
        int index=t.getSelectedRow();
        return ctm.cs.get(index);
    }

    public void updateData(){
        ctm.cs=new CategoryService().list();
        t.updateUI();
        t.getSelectionModel().setSelectionInterval(0,0);

        if (ctm.cs.size()==0){
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
        }
        if (ctm.cs.size()!=0){
            bEdit.setEnabled(true);
            bDelete.setEnabled(true);
        }
    }

    public void addListener(){
        CategoryListener listener=new CategoryListener();
        bAdd.addActionListener(listener);
        bEdit.addActionListener(listener);
        bDelete.addActionListener(listener);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(CategoryPanel.instance);
    }
}
