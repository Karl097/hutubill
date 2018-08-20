package gui.listener;

import entity.Category;
import entity.Record;
import gui.panel.DetailPanel;
import service.DetailService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        DetailPanel p=DetailPanel.instance;

        JButton b=(JButton)e.getSource();
        if (b==p.bAll){
            p.dtm.rs=new DetailService().list();
            p.t.updateUI();
        }
        if (b==p.bSearch){
            Category c=p.getSelectedCategory();
            p.dtm.rs=new DetailService().list(c.id);
            p.t.updateUI();
        }

        if (b == p.bEdit) {
            Record r = p.getSelectedRecord();
            String spend = JOptionPane.showInputDialog("修改消费金额", r.spend);
            if (spend.length() == 0) {
                JOptionPane.showMessageDialog(p, "消费金额不能为零");
                return;
            }
            new DetailService().update(Integer.parseInt(spend), r.id, r.cid, r.comment, r.date);
            p.updateData();
        }

        if (b == p.bDelete) {
            Record r = p.getSelectedRecord();
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "确认要删除？"))
                return;
            new DetailService().delete(r.id);
            p.updateData();
        }

    }
}
