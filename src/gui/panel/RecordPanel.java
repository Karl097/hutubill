package gui.panel;

import entity.Category;
import gui.listener.RecordListener;
import gui.model.CategoryComboBoxModel;
import org.jdesktop.swingx.JXDatePicker;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class RecordPanel extends WorkingPanel {
    //设置皮肤
    static {
        GUIUtil.useLNF();
    }

    //单例模式
    public static RecordPanel instance=new RecordPanel();

    //各种组件
    JLabel lSpend=new JLabel("花费（￥）");
    JLabel lCategory=new JLabel("分类");
    JLabel lComment=new JLabel("备注");
    JLabel lDate=new JLabel("日期");

    public JTextField tfSpend=new JTextField("0");

    public CategoryComboBoxModel cbModel=new CategoryComboBoxModel();
    public JComboBox<Category> cbCategory=new JComboBox<>(cbModel);
    public JTextField tfComment=new JTextField();
    public JXDatePicker datepick=new JXDatePicker(new Date());

    public JButton bSubmit=new JButton("记一笔");

    public RecordPanel(){
        GUIUtil.setColor(ColorUtil.grayColor,lSpend,lCategory,lComment,lDate);
        GUIUtil.setColor(ColorUtil.blueColor,bSubmit);

        this.setLayout(new BorderLayout());

        this.add(pInput(),BorderLayout.NORTH);
        this.add(pSubmit(),BorderLayout.CENTER);

        addListener();
    }

    //数据面板
    private JPanel pInput(){
        JPanel p=new JPanel();

        int gap=40;
        p.setLayout(new GridLayout(4,2,gap,gap));

        p.add(lSpend);
        p.add(tfSpend);
        p.add(lCategory);
        p.add(cbCategory);
        p.add(lComment);
        p.add(tfComment);
        p.add(lDate);
        p.add(datepick);

        return p;
    }

    //按钮面板
    private JPanel pSubmit(){
        JPanel p=new JPanel();

        p.setLayout(new FlowLayout());

        p.add(bSubmit);

        return p;
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(RecordPanel.instance);
    }

    @Override
    public void addListener() {
        RecordListener listener=new RecordListener();

        bSubmit.addActionListener(listener);
    }

    public Category getSelectedCategory(){
        return (Category) cbCategory.getSelectedItem();
    }
    @Override
    public void updateData() {
        cbModel.cs=new CategoryService().list();
        cbCategory.updateUI();

        resetInput();
        tfSpend.grabFocus();
    }

    public void resetInput(){
        tfSpend.setText("0");
        tfComment.setText("");

        if (0!=cbModel.cs.size())
            cbCategory.setSelectedIndex(0);
        datepick.setDate(new Date());
    }
}
