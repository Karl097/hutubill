package gui.panel;

import gui.listener.ToolBarListener;
import util.CenterPanel;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    //先设置水晶皮肤
    static {
        GUIUtil.useLNF();
    }

    //设置为单例模式
    public static MainPanel instance=new MainPanel();

    //各种按钮
    public JToolBar tb=new JToolBar();

    public JButton bSpend=new JButton();
    public JButton bRecord=new JButton();
    public JButton bCategory=new JButton();
    public JButton bDetail=new JButton();
    public JButton bReport=new JButton();
    public JButton bConfig=new JButton();
    public JButton bBackup=new JButton();
    public JButton bRecover=new JButton();

    //工作面板
    public CenterPanel workingPanel;

    //主面板
    private MainPanel(){
        GUIUtil.setImageIcon(bSpend,"home.png","消费一览");
        GUIUtil.setImageIcon(bRecord,"record.png","记一笔");
        GUIUtil.setImageIcon(bCategory,"category2.png","消费分类");
        GUIUtil.setImageIcon(bDetail,"category1.png","消费记录");
        GUIUtil.setImageIcon(bReport,"report.png","月消费报表");
        GUIUtil.setImageIcon(bConfig,"config.png","设置");
        GUIUtil.setImageIcon(bBackup,"backup.png","备份");
        GUIUtil.setImageIcon(bRecover,"restore.png","恢复");

        tb.add(bSpend);
        tb.add(bRecord);
        tb.add(bCategory);
        tb.add(bDetail);
        tb.add(bReport);
        tb.add(bConfig);
        tb.add(bBackup);
        tb.add(bRecover);
        tb.setFloatable(false);

        workingPanel=new CenterPanel(0.8);

        setLayout(new BorderLayout());

        add(tb,BorderLayout.NORTH);
        add(workingPanel,BorderLayout.CENTER);

        //增加工具栏监听
        addListener();
    }

    //工具栏监听
    private void addListener(){
        ToolBarListener listener=new ToolBarListener();

        bSpend.addActionListener(listener);
        bRecord.addActionListener(listener);
        bCategory.addActionListener(listener);
        bDetail.addActionListener(listener);
        bReport.addActionListener(listener);
        bConfig.addActionListener(listener);
        bBackup.addActionListener(listener);
        bRecover.addActionListener(listener);
    }
    public static void main(String[] args) {
        GUIUtil.showPanel(MainPanel.instance,1);
    }
}
