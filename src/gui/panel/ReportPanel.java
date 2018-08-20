package gui.panel;

import service.ReportService;
import util.ChartUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class ReportPanel extends WorkingPanel {
    //设置皮肤
    static {
        GUIUtil.useLNF();
    }

    //单例模式
    public static ReportPanel instance=new ReportPanel();

    //组件
    public JLabel l=new JLabel();

    public ReportPanel(){
        this.setLayout(new BorderLayout());

        Image i=ChartUtil.getImage(new ReportService().listThisMonthRecords(),400,300);
        ImageIcon icon=new ImageIcon(i);

        l.setIcon(icon);
        this.add(l);
        addListener();
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(ReportPanel.instance);
    }

    @Override
    public void addListener() {

    }

    @Override
    public void updateData() {
        Image i=ChartUtil.getImage(new ReportService().listThisMonthRecords(),350,250);
        ImageIcon icon = new ImageIcon(i);
        l.setIcon(icon);
    }
}
