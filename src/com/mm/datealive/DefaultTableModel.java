package com.mm.datealive;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.alibaba.fastjson2.JSON;
import com.intellij.ide.util.PropertiesComponent;

/**
 * description:
 * @author: humian49712
 * @date: 2023/10/17
 */
public class DefaultTableModel extends AbstractTableModel {

    private String[] headerRecord;

    private String[] columnRecord;

    private List dataList;

    private Class clazz;

    public DefaultTableModel(String[] headerRecord, String[] columnRecord, List dataList, Class clazz) {
        this.headerRecord = headerRecord;
        this.columnRecord = columnRecord;
        if (dataList == null) {
            dataList = new ArrayList();
        }
        this.dataList = dataList;
        this.clazz = clazz;
    }

    /**
     * 返回一共有多少行
     * @return
     */
    @Override
    public int getRowCount() {
        return dataList.size();
    }

    /**
     * 返回一共有多少列
     * @return
     */
    @Override
    public int getColumnCount() {
        return headerRecord.length;
    }

    /**
     * 返回一共有多少列
     * @param columnIndex
     * @return
     */
    @Override
    public String getColumnName(int columnIndex) {
        return headerRecord[columnIndex];
    }

    /**
     * 单元格是否可以修改
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 1) {
            return true;
        }
        return false;
    }

    /**
     * 每一个单元格里的值
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Field field = clazz.getDeclaredField(columnRecord[columnIndex]);
            field.setAccessible(true);
            return field.get(dataList.get(rowIndex));
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        //String content = value.toString().replaceAll("\\n", " ");
        String content = value.toString();
        try {
            Field field = clazz.getDeclaredField(columnRecord[col]);
            field.setAccessible(true);
            field.set(dataList.get(row), content);
        } catch (Exception e) {

        }
        fireTableCellUpdated(row, col);
        switch(col) {
            case 1 :
                ResourceUtils.dataColumn1[row] = value.toString();
                /*String dataColumn1Str = PropertiesComponent.getInstance().getValue("dataaliveDataListColumn1");
                String[] dataColumn1 = new String[10];
                dataColumn1 = JSON.parseArray(dataColumn1Str).toArray(dataColumn1);
                dataColumn1[row] = value.toString();*/
                PropertiesComponent.getInstance().setValue("dataaliveDataListColumn1", JSON.toJSONString(ResourceUtils.dataColumn1));
                break;
            case 2 :
                ResourceUtils.dataColumn2[row] = value.toString();
                /*String dataColumn2Str = PropertiesComponent.getInstance().getValue("dataaliveDataListColumn2");
                String[] dataColumn2 = new String[10];
                dataColumn2 = JSON.parseArray(dataColumn2Str).toArray(dataColumn2);
                if (dataColumn2.length > 10) {
                    dataColumn2 = new String[10];
                }
                dataColumn2[row] = value.toString();*/
                PropertiesComponent.getInstance().setValue("dataaliveDataListColumn2", JSON.toJSONString(ResourceUtils.dataColumn2));
                break;
            default:
                break;
        }
    }

    public List getDataList() {
        return dataList;
    }

    public Class getClazz() {
        return clazz;
    }

}
