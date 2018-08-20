package util;

import gui.panel.ConfigPanel;
import gui.panel.WorkingPanel;

import javax.swing.*;
import java.awt.*;
//Swing并没有提供一种可以很简单就居中的布局器，但是这样的布局器又很常见，所以在这里就自己开发一个专门用于居中的面板
public class CenterPanel extends JPanel {
    private double rate;//拉伸比例
    private JComponent c;//显示的组件
    private boolean stretch;//是否拉伸

    //构造方法
    public CenterPanel (double rate,boolean stretch){
        this.setLayout(null);
        this.rate=rate;
        this.stretch =stretch;
    }
    //构造方法
    public CenterPanel (double rate){
        this(rate,true);
    }

    //把这个容器中的组件都移出，然后把新的组件加进来，并且调用updateUI进行界面渲染
    public void show(JComponent p){
        this.c=p;
        Component cs []=getComponents();
        for (Component c:cs){
            remove(c);
        }
        add(p);

        if (p instanceof WorkingPanel)
            ((WorkingPanel)p).updateData();
        this.updateUI();
    }

    //放置组件
    public void repaint(){
        if (c!=null){
            Dimension containerSize=this.getSize();
            Dimension componentSize=c.getPreferredSize();

            if (stretch){
                c.setSize((int)(containerSize.width*rate),(int)(containerSize.height*rate));
            }
            else{
                c.setSize(componentSize);
            }

            c.setLocation(containerSize.width/2-c.getSize().width/2,containerSize.height/2-c.getSize().height/2);
        }
        super.repaint();
    }

    public static void main(String[] args) {
        JFrame f=new JFrame();
        f.setSize(200,200);
        f.setLocationRelativeTo(null);
        CenterPanel cp=new CenterPanel(0.85,true);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        JButton b=new JButton("Demo");
        cp.show(b);
    }
}
