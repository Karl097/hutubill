package gui.panel;

import gui.listener.ConfigListener;
import service.ConfigService;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends WorkingPanel{
    //设置皮肤
    static {
        GUIUtil.useLNF();
    }

    //单例模式
    public static ConfigPanel instance=new ConfigPanel();

    //组件
    public JLabel lBudget=new JLabel("本月预算（￥）");
    public JTextField tfBudget=new JTextField("0");

    public JLabel lMysql=new JLabel("Mysql安装目录：");
    public JTextField tfMysqlPath =new JTextField("");

    public JButton bSubmit=new JButton("更新");

    //主面板
    public ConfigPanel(){
        GUIUtil.setColor(ColorUtil.grayColor,lBudget,lMysql);
        GUIUtil.setColor(ColorUtil.blueColor,bSubmit);

        this.setLayout(new BorderLayout());
        this.add(north(),BorderLayout.NORTH);
        this.add(center(),BorderLayout.CENTER);

        addListener();
    }

    //数据面板
    private JPanel north(){
        JPanel p=new JPanel();
        int gap=40;
        p.setLayout(new GridLayout(4,1,gap,gap));

        p.add(lBudget);
        p.add(tfBudget);
        p.add(lMysql);
        p.add(tfMysqlPath);

        return p;
    }

    //按钮面板
    private JPanel center(){
        JPanel p=new JPanel();
        p.setLayout(new FlowLayout());

        p.add(bSubmit);

        return p;
    }

    //按钮监听
    public void addListener(){
        ConfigListener listener=new ConfigListener();
        bSubmit.addActionListener(listener);
    }

    @Override
    public void updateData() {
        String budget=new ConfigService().get(ConfigService.budget);
        String mysqlPath=new ConfigService().get(ConfigService.mysqlPath);

        tfBudget.setText(budget);
        tfMysqlPath.setText(mysqlPath);

        tfBudget.grabFocus();
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(ConfigPanel.instance);
    }
}
