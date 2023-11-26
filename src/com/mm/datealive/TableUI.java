package com.mm.datealive;

import com.alibaba.fastjson2.JSON;
import com.intellij.icons.AllIcons;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.IdeaPluginDescriptorImpl;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.IconButton;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.components.JBLabel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * description:
 * 
 * @author: humian49712
 * @date: 2023/11/21
 */
public class TableUI {
    private JPanel mainPanel;
    private JTable table;

    public TableUI(Project project) {
        String[] header = new String[] {"代号", "标签", "生成内容"};
        String[] column = new String[] {"no", "name", "content"};
        List<ItemData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new ItemData(Integer.valueOf(ResourceUtils.dataColumn0[i]), ResourceUtils.dataColumn1[i], ResourceUtils.dataColumn2[i]));
        }
        DefaultTableModel defaultTableModel = new DefaultTableModel(header, column, list, ItemData.class);
        table.setModel(defaultTableModel);
        table.setRowHeight(30);

        TableColumn column0 = table.getColumnModel().getColumn(0);
        column0.setPreferredWidth(100);

        /*List<? extends IdeaPluginDescriptor> loadedPlugins = PluginManager.getLoadedPlugins();
        for (IdeaPluginDescriptor item : loadedPlugins) {
            IdeaPluginDescriptorImpl ideaPluginDescriptor =  (IdeaPluginDescriptorImpl)item;
            String name = ideaPluginDescriptor.getName();
            Path path = ideaPluginDescriptor.getPluginPath();
            if (name.contains("date")) {
                name.toCharArray();
            }
        }*/
       /* File path = plugin.getPath();
        String pluginPath= path.getAbsolutePath();*/

        column0.setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
                JLabel jLabel = new JBLabel();
                //ImageIcon imageIcon = IconConstants.DATEALIVE_IMAGEICON[(Integer)value];
                /*Icon icon = IconConstants.DATEALIVE_ICON[(Integer)value];
                ImageIcon imageIcon = ((IconLoader.CachedImageIcon)icon).getRealIcon();
                Image img = imageIcon.getImage();
                Image newimg = img.getScaledInstance(100, 30, java.awt.Image.SCALE_SMOOTH);
                ImageIcon newIcon = new ImageIcon(newimg);
                jLabel.setIcon(newIcon);*/
                Icon icon = IconConstants.DATEALIVE_ICON[(Integer)value];
                jLabel.setIcon(icon);
                return jLabel;
            }
        });

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);

        TableColumn column1 = table.getColumnModel().getColumn(1);
        column1.setPreferredWidth(80);
        column1.setCellRenderer(rightRenderer);
        TableColumn column2 = table.getColumnModel().getColumn(2);
        column2.setPreferredWidth(100);
        column2.setCellRenderer(rightRenderer);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int column = table.columnAtPoint(e.getPoint());
                if (column == 2) {
                    String dataColumn2Str = PropertiesComponent.getInstance().getValue("dataaliveDataListColumn2");
                    String[] dataColumn2 = new String[10];
                    dataColumn2 = JSON.parseArray(dataColumn2Str).toArray(dataColumn2);
                    String content = dataColumn2[row];

                    JScrollPane jScrollPane = new JScrollPane();
                    JTextArea jTextArea = new JTextArea();
                    jTextArea.setLineWrap(true);
                    jScrollPane.setViewportView(jTextArea);
                    jTextArea.setText(content);

                    jTextArea.addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusLost(FocusEvent e) {
                            String content = jTextArea.getText();
                            // content = content.replaceAll("\\\\n", "");
                            //defaultTableModel.setValueAt(content, row, column);
                            table.setValueAt(content, row, column);
                        }
                    });
                    /*jTextArea.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseExited(MouseEvent e) {
                            super.mouseExited(e);
                            defaultTableModel.setValueAt(jTextArea.getText(), row, column);
                        }
                    });*/

                    JBPopup jBPopupTable = JBPopupFactory.getInstance().createComponentPopupBuilder(jScrollPane, null)
                        .setMovable(true).setResizable(false).setFocusable(true).setMinSize(new Dimension(250, 200))
                        .setCancelOnClickOutside(true).createPopup();
                    jBPopupTable.setRequestFocus(true);
                    jBPopupTable.showInCenterOf(table);
                    /*jBPopupTable.showInScreenCoordinates(table,
                        new Point(table.getX() + (table.getWidth() - jTextArea.getWidth()) / 2,
                            table.getY() + (table.getHeight() - jTextArea.getHeight()) / 2));*/
                }
            }

        });
    }

    private void initItem() {
        String[] dataColumn0 = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        String[] dataColumn1 =
            new String[] {"public", "final", "for", "break", "try", "continue", "switch", "if", "list", "private"};
        String[] dataColumn2 =
            new String[] {"public", "final", "for", "break", "try", "continue", "switch", "if", "list", "private"};
        PropertiesComponent.getInstance().setValue("dataaliveDataListColumn0", JSON.toJSONString(dataColumn0));
        PropertiesComponent.getInstance().setValue("dataaliveDataListColumn1", JSON.toJSONString(dataColumn1));
        PropertiesComponent.getInstance().setValue("dataaliveDataListColumn2", JSON.toJSONString(dataColumn2));
    }

    public JComponent getComponent() {
        return table;
    }
}
