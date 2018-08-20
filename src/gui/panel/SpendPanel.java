package gui.panel;

import gui.page.SpendPage;
import service.SpendService;
import util.CircleProgressBar;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class SpendPanel extends WorkingPanel{
    //设置皮肤
    static {
        GUIUtil.useLNF();
    }

    //单例模式
    public static SpendPanel instance=new SpendPanel();

    //设置为public方便监听
    public JLabel lMonthSpend=new JLabel("本月消费");
    public JLabel lTodaySpend=new JLabel("今日消费");
    public JLabel lAvgSpendPerDay=new JLabel("日均消费");
    public JLabel lMonthLeft=new JLabel("本月剩余");
    public JLabel lDayAvgAvailiable =new JLabel("日均可用");
    public JLabel lMonthLeftDay=new JLabel("距离月末");

    public JLabel vMonthSpend=new JLabel("￥2300");
    public JLabel vTodaySpend=new JLabel("￥25");
    public JLabel vAvgSpendPerDay=new JLabel("￥120");
    public JLabel vMonthLeft=new JLabel("￥2084");
    public JLabel vDayAvgAviliable=new JLabel("389");
    public JLabel vMonthLeftDay=new JLabel("15天");

    //环形进度条
    CircleProgressBar bar;

    //主面板
    public SpendPanel(){
        this.setLayout(new BorderLayout());

        bar=new CircleProgressBar();
        bar.setBackgroundColor(ColorUtil.blueColor);

        GUIUtil.setColor(ColorUtil.grayColor,lMonthSpend,lTodaySpend,lAvgSpendPerDay,lMonthLeft, lDayAvgAvailiable,lMonthLeftDay,vAvgSpendPerDay,vMonthLeft,vDayAvgAviliable,vMonthLeftDay);
        GUIUtil.setColor(ColorUtil.blueColor,vMonthSpend,vTodaySpend);

        vMonthSpend.setFont(new Font("微软雅黑",Font.BOLD,23));
        vTodaySpend.setFont(new Font("微软雅黑",Font.BOLD,23));

        add(center(),BorderLayout.CENTER);
        add(south(),BorderLayout.SOUTH);
    }

    //中间面板
    private JPanel center(){
        JPanel p=new JPanel();

        p.setLayout(new BorderLayout());

        p.add(west(),BorderLayout.WEST);
        p.add(center2(),BorderLayout.CENTER);

        return p;
    }

    //中间左边数据面板
    private Component west(){
        JPanel p=new JPanel();

        p.setLayout(new GridLayout(4,1));

        p.add(lMonthSpend);
        p.add(vMonthSpend);
        p.add(lTodaySpend);
        p.add(vTodaySpend);

        return p;
    }

    //放置环形进度条的面板
    private Component center2(){
        return bar;
    }

    //下方数据面板
    private JPanel south(){
        JPanel p=new JPanel();

        p.setLayout(new GridLayout(2,4));

        p.add(lAvgSpendPerDay);
        p.add(lMonthLeft);
        p.add(lDayAvgAvailiable);
        p.add(lMonthLeftDay);
        p.add(vAvgSpendPerDay);
        p.add(vMonthLeft);
        p.add(vDayAvgAviliable);
        p.add(vMonthLeftDay);

        return p;
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(SpendPanel.instance);
    }

    @Override
    public void addListener() {

    }

    @Override
    public void updateData() {
        SpendPage sPage=new SpendService().getSpendPage();

        vMonthSpend.setText(sPage.monthSpend);
        vTodaySpend.setText(sPage.todaySpend);
        vAvgSpendPerDay.setText(sPage.avgSpendPerDay);
        vMonthLeft.setText(sPage.monthAvailable);
        vDayAvgAviliable.setText(sPage.dayAvgAvailable);
        vMonthLeftDay.setText(sPage.monthLeftDay);

        bar.setProgress(sPage.usagePercentage);
        if (sPage.isOverSpend){
            vMonthLeft.setForeground(ColorUtil.warningColor);
            vMonthSpend.setForeground(ColorUtil.warningColor);
            vTodaySpend.setForeground(ColorUtil.warningColor);
        }else {
            vMonthLeft.setForeground(ColorUtil.grayColor);
            vMonthSpend.setForeground(ColorUtil.blueColor);
            vTodaySpend.setForeground(ColorUtil.blueColor);
        }
        bar.setForegroundColor(ColorUtil.getByPercentage(sPage.usagePercentage));
        addListener();
    }
}
