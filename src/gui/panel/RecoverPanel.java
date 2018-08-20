package gui.panel;

import gui.listener.RecoverListener;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;

public class RecoverPanel extends WorkingPanel{
    //设置皮肤
    static {
        GUIUtil.useLNF();
    }

    //单例模式
    public static RecoverPanel instance=new RecoverPanel();

    //组件
    JButton bRecover =new JButton("恢复");

    public RecoverPanel(){
        GUIUtil.setColor(ColorUtil.blueColor, bRecover);

        this.add(bRecover);
        addListener();
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(RecoverPanel.instance);
    }

    @Override
    public void addListener() {
        RecoverListener listener=new RecoverListener();
        bRecover.addActionListener(listener);
    }

    @Override
    public void updateData() {

    }
}
