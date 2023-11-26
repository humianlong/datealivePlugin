package com.mm.datealive;

/**
 * description:
 * @author: humian49712
 * @date: 2023/11/21
 */
public class ItemData {

    private int no;

    private String name;

    private String content;

    public ItemData() {
    }

    public ItemData(int no, String name, String content) {
        this.no = no;
        this.name = name;
        this.content = content;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
