package util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
//在开发图形界面的过程中，有很多功能在各个地方都会用到，并且重复使用的概率比较高。
// 比如判断一个输入框是否是数字，是否是空，是否为0等等类似的，所以把这些使用比较普遍的功能，抽象到一个工具类里。
// 这样以后再用的时候，就直接调用就可以了，而不需要每次都单独再写一遍。
public class GUIUtil {
    public static String imageFolder="D:\\JAVA\\hutubill\\img";

    //设置图标和提示文字
    public static void setImageIcon(JButton b,String fileName,String tip){
        ImageIcon i=new ImageIcon(new File(imageFolder,fileName).getAbsolutePath());
        b.setIcon(i);
        b.setPreferredSize(new Dimension(61,81));
        b.setToolTipText(tip);
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setText(tip);
    }

    //组件设置前景色
    public static void setColor(Color color,JComponent... cs){
        for (JComponent c:cs){
            c.setForeground(color);
        }
    }

    //设置水晶皮肤
    public static void useLNF() {
        try {
            UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //显示面板内容
    public static void showPanel(JPanel p,double stretchRate){
        GUIUtil.useLNF();
        JFrame f=new JFrame();
        f.setSize(500,500);
        f.setLocationRelativeTo(null);
        CenterPanel cp=new CenterPanel(stretchRate);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        cp.show(p);
    }

    public static void showPanel(JPanel p){
        showPanel(p,0.85);
    }

    //检查是否为空
    public static boolean checkEmpty(JTextField tf,String input){
        String text=tf.getText().trim();
        if (text.length()==0){
            JOptionPane.showMessageDialog(null,input+"不能为空");
            tf.grabFocus();
            return false;
        }
        return true;
    }

    //检查是否为整数
    public static boolean checkNumber(JTextField tf,String input){
        if (!checkEmpty(tf,input))
            return false;

        String text=tf.getText().trim();
        try {
            Integer.parseInt(text);
            return true;
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,input+"必须为整数");
            tf.grabFocus();
            return false;
        }
    }

    //检查是否为零
    public static boolean checkZero(JTextField tf,String input){
        if (!checkNumber(tf,input))
            return false;

        String text=tf.getText().trim();
        if (0==Integer.parseInt(text)){
            JOptionPane.showMessageDialog(null,input+"不能为零");
            tf.grabFocus();
            return false;
        }
        return true;
    }

    public static int getInt(JTextField tf){
        return Integer.parseInt(tf.getText());
    }
}
