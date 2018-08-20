package gui.listener;

import gui.panel.ConfigPanel;
import gui.panel.MainPanel;
import gui.panel.RecoverPanel;
import service.ConfigService;
import util.MysqlUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RecoverListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RecoverPanel p=RecoverPanel.instance;

        String mysqlPath=new ConfigService().get(ConfigService.mysqlPath);
        if (mysqlPath.length()==0){
            JOptionPane.showMessageDialog(p,"恢复前请配置路径");
            MainPanel.instance.workingPanel.show(ConfigPanel.instance);
            ConfigPanel.instance.tfMysqlPath.grabFocus();
            return;
        }
        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new File("hutubill.sql"));
        fc.setFileFilter(new FileFilter() {

            @Override
            public String getDescription() {
                return ".sql";
            }

            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".sql");
            }
        });

        int returnVal =  fc.showSaveDialog(p);
        File file = fc.getSelectedFile();

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //如果保存的文件名没有以.sql结尾，自动加上.sql
            if(!file.getName().toLowerCase().endsWith(".sql"))
                file = new File(file.getParent(),file.getName()+".sql");

            try {
                MysqlUtil.recover(mysqlPath, file.getAbsolutePath());
                JOptionPane.showMessageDialog(p, "恢复成功");
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(p, "恢复失败\r\n,错误:\r\n"+e1.getMessage());
            }

        }
    }
}
