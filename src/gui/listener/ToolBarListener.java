package gui.listener;

import gui.panel.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//设计一个独立的监听器类ToolBarListener ，实现接口ActionListener ，重写actionPerformed方法。
//        这个监听器是为工具栏上的几个按钮添加的，并且这几个按钮都使用这么一个监听器。
//        通过ActionEvent.getSource()获取事件是哪个按钮发出来的，根据不同的按钮，发出切换不同的功能面板。
public class ToolBarListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        MainPanel p=MainPanel.instance;
        JButton b=(JButton)e.getSource();

        if (b==p.bSpend)
            p.workingPanel.show(SpendPanel.instance);
        if (b==p.bRecord)
            p.workingPanel.show(RecordPanel.instance);
        if (b==p.bCategory)
            p.workingPanel.show(CategoryPanel.instance);
        if (b==p.bDetail)
            p.workingPanel.show(DetailPanel.instance);
        if (b==p.bReport)
            p.workingPanel.show(ReportPanel.instance);
        if (b==p.bConfig)
            p.workingPanel.show(ConfigPanel.instance);
        if (b==p.bBackup)
            p.workingPanel.show(BackupPanel.instance);
        if (b==p.bRecover)
            p.workingPanel.show(RecoverPanel.instance);
    }
}
