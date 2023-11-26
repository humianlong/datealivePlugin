package com.mm.datealive;

import a.e.c.S;
import com.alibaba.fastjson2.JSON;
import com.intellij.ide.util.PropertiesComponent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 * 
 * @author: humian49712
 * @date: 2023/11/17
 */
public class ResourceUtils {

    public static Map<String, Integer> noMap = new HashMap<>();

    public static Map<Integer, ItemUI> map = new HashMap<>();

    public static Map<Integer, Color> colorMap = new HashMap<>();

    public static String dataColumn0Str;
    public static String[] dataColumn0 = new String[10];
    public static String dataColumn1Str;
    public static String[] dataColumn1 = new String[10];
    public static String dataColumn2Str;
    public static String[] dataColumn2 = new String[10];

    static {
        try {
            setItem();
        } catch (Exception e) {
            initItem();
        }

        noMap.put("1", 0);
        noMap.put("2", 1);
        noMap.put("3", 2);
        noMap.put("4", 3);
        noMap.put("5", 4);
        noMap.put("6", 5);
        noMap.put("7", 6);
        noMap.put("8", 7);
        noMap.put("9", 8);
        noMap.put("0", 9);


        map.put(1, genNode("1"));
        map.put(2, genNode("2"));
        map.put(3, genNode("3"));
        map.put(4, genNode("4"));
        map.put(5, genNode("5"));
        map.put(6, genNode("6"));
        map.put(7, genNode("7"));
        map.put(8, genNode("8"));
        map.put(9, genNode("9"));
        map.put(0, genNode("0"));

        colorMap.put(0, new Color(0x7B047B));
        colorMap.put(1, new Color(0xEEEEFA));
        colorMap.put(2, new Color(0x70707F));
        colorMap.put(3, new Color(0x1F181F));
        colorMap.put(4, new Color(0x356BF3));
        colorMap.put(5, new Color(0xF10E77));
        colorMap.put(6, new Color(0xF1E35E));
        colorMap.put(7, new Color(0x1CDB78));
        colorMap.put(8, new Color(0xF8A358));
        colorMap.put(9, new Color(0xE3C9E8));


    }

    private static void setItem() {
        dataColumn0Str = PropertiesComponent.getInstance().getValue("dataaliveDataListColumn0");
        dataColumn0 = JSON.parseArray(dataColumn0Str).toArray(dataColumn0);
        dataColumn1Str = PropertiesComponent.getInstance().getValue("dataaliveDataListColumn1");
        dataColumn1 = JSON.parseArray(dataColumn1Str).toArray(dataColumn1);
        dataColumn2Str = PropertiesComponent.getInstance().getValue("dataaliveDataListColumn2");
        dataColumn2 = JSON.parseArray(dataColumn2Str).toArray(dataColumn2);
    }

    private static void initItem() {
        dataColumn0 = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        dataColumn1 = new String[] {"public", "final", "for", "break", "try", "continue", "switch", "if", "list", "private"};
        dataColumn2 = new String[] {"public", "final", "for", "break", "try", "continue", "switch", "if", "list", "private"};
        PropertiesComponent.getInstance().setValue("dataaliveDataListColumn0", JSON.toJSONString(dataColumn0));
        PropertiesComponent.getInstance().setValue("dataaliveDataListColumn1", JSON.toJSONString(dataColumn1));
        PropertiesComponent.getInstance().setValue("dataaliveDataListColumn2", JSON.toJSONString(dataColumn2));
        setItem();
    }

    public static ItemUI genNode(String no) {
        int index = noMap.get(no);
        ItemUI itemUI = new ItemUI();
        itemUI.getName().setText(dataColumn1[index]);
        switch (no) {
            case "0":
                itemUI.getName().setForeground(new Color(0x7B047B));
                break;
            case "1":
                itemUI.getName().setForeground(new Color(0xEEEEFA));
                break;
            case "2":
                itemUI.getName().setForeground(new Color(0x70707F));
                break;
            case "3":
                itemUI.getName().setForeground(new Color(0x1F181F));
                break;
            case "4":
                itemUI.getName().setForeground(new Color(0x356BF3));
                break;
            case "5":
                itemUI.getName().setForeground(new Color(0xF10E77));
                break;
            case "6":
                itemUI.getName().setForeground(new Color(0xF1E35E));
                break;
            case "7":
                itemUI.getName().setForeground(new Color(0x1CDB78));
                break;
            case "8":
                itemUI.getName().setForeground(new Color(0xF8A358));
                break;
            case "9":
                itemUI.getName().setForeground(new Color(0xE3C9E8));
                break;
        }
        //itemUI.getName().setForeground(colorMap.get(no));
        itemUI.getImg().setIcon(IconConstants.DATEALIVE_ICON[Integer.valueOf(no)]);
        return itemUI;
    }

    public static String genWhite(int countOfWhite) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < countOfWhite; i++) {
            stringBuilder.append(' ');
        }
        return stringBuilder.toString();
    }

    public static String getDetail(String i, int step) {
        int index = noMap.get(i);
        String content = dataColumn2[index];
        content = content.replaceAll("\n", "\n" + genWhite(step));
        return content;
    }

    public static String getPrivateStr(int countOfWhite) {
        return "private";
    }
    public static String getPublicStr(int countOfWhite) {
        return "public";
    }
    public static String getFinalStr(int countOfWhite) {
        return "final";
    }
    public static String getForStr(int countOfWhite) {
        String step = genWhite(countOfWhite);
        String forStr = "for (int i = 0; i < arr.size(); i++) {\n" +
                step + "    \n" +
                step + "}";
        return forStr;
    }
    public static String getBreakStr(int countOfWhite) {
        return "break;";
    }
    public static String getTryStr(int countOfWhite) {
        String step = genWhite(countOfWhite);
        String tryStr = "try {\n" +
                step + "    \n" +
                step + "} catch (Exception e) {\n" +
                step + "    \n" +
                step + "}";
        return tryStr;
    }
    public static String getContinueStr(int countOfWhite) {
        return "continue;";
    }
    public static String getSwitchStr(int countOfWhite) {
        String step = genWhite(countOfWhite);
        String switchStr =  "switch(i) { \n" +
                step + "    case i : \n" +
                step + "        break; \n" +
                step + "    default: \n" +
                step + "        break;\n" +
                step + "}";
        return switchStr;
    }
    public static String getIfStr(int countOfWhite) {
        String step = genWhite(countOfWhite);
        String ifStr = "if () {\n" +
                step + "    \n" +
                step + "} else {\n" +
                step + "    \n" +
                step + "}";
        return ifStr;
    }
    public static String getListStr(int countOfWhite) {
        return "List list = new ArrayList<>();";
    }


}
