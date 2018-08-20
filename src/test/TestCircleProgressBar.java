package test;

import util.CircleProgressBar;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestCircleProgressBar {
    public static void main(String[] args) {
        GUIUtil.useLNF();

        //面板
        JPanel p=new JPanel();
        //环形进度条
        CircleProgressBar cpb=new CircleProgressBar();
        cpb.setBackgroundColor(ColorUtil.blueColor);
        cpb.setProgress(0);

        //按钮
        JButton b=new JButton("Test");

        //添加组件
        p.setLayout(new BorderLayout());
        p.add(cpb,BorderLayout.CENTER);
        p.add(b,BorderLayout.SOUTH);

        //显示面板
        GUIUtil.showPanel(p);

        //给按钮添加监听
        //使用长耗时任务线程SwingWorker
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SwingWorker(){

                    @Override
                    protected Object doInBackground() throws Exception {
                        for (int i=0;i<100;i++){
                            cpb.setProgress(i+1);
                            cpb.setForegroundColor(ColorUtil.getByPercentage(i+1));
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        }
                        return null;
                    }
                }.execute();
            }
        });
    }
}
