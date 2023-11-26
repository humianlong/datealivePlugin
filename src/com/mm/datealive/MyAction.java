package com.mm.datealive;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

import org.jetbrains.annotations.NotNull;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.util.TextRange;
import com.intellij.ui.components.JBList;

/**
 * description:
 * 
 * @author: humian49712
 * @date: 2023/11/17
 */
public class MyAction extends AnAction {

    private JBPopup jBPopup;

    @Override
    public void update(@NotNull AnActionEvent event) {
        // 启用或禁用Action
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        MyAction that = this;

        JBPopupFactory instance = JBPopupFactory.getInstance();
        JBList jList = new JBList<String>();
        Object[] list = new Object[10];
        list[0] = 1;
        list[1] = 2;
        list[2] = 3;
        list[3] = 4;
        list[4] = 5;
        list[5] = 6;
        list[6] = 7;
        list[7] = 8;
        list[8] = 9;
        list[9] = 0;

        jList.setListData(list);
        jList.setCellRenderer(new ListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                ItemUI itemUI = ResourceUtils.genNode(value.toString());
                itemUI.setMyAction(that);
                return itemUI.getMainPanel();
            }
        });

        Project project = event.getProject();
        Editor textEditor = FileEditorManager.getInstance(project).getSelectedTextEditor();


        jList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                java.util.List<Character> list = new ArrayList(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0'));
                Character c = e.getKeyChar();
                if (list.contains(c)) {
                    final Editor editor = textEditor;
                    final Document document = editor.getDocument();
                    // Construct the runnable to substitute the string at offset 0 in the document
                    List<Caret> caretList = editor.getCaretModel().getAllCarets();
                    for (Caret caret : caretList) {

                        // 获取行号
                        int line = document.getLineNumber(caret.getOffset());
                        // 获取行内容
                        String text = document.getText(new TextRange(document.getLineStartOffset(line), document.getLineEndOffset(line)));
                        int countOfWhite = countWhite(text);
                        String insertStr = ResourceUtils.getDetail(c.toString(), countOfWhite);
                        Runnable runnable = () -> {
                            document.insertString(caret.getOffset(), insertStr);
                            caret.moveToOffset(caret.getOffset() + insertStr.length());
                        };

                        // Make the document change in the context of a write action.
                        WriteCommandAction.runWriteCommandAction(project, runnable);
                    }
                    jBPopup.cancel();
                }
            }
        });

        jBPopup = instance.createComponentPopupBuilder(jList, null)
                .setTitle("datealive")
                .setMovable(true)
                .setResizable(false)
                .setFocusable(true)
                .setMinSize(new Dimension(160,320)).setCancelOnClickOutside(true)
                .createPopup();
        jBPopup.setRequestFocus(true);



        jBPopup.showInBestPositionFor(textEditor);
        //jTextField.requestFocus();
        jList.requestFocus();

    }

    public int countWhite(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == ' ' ) {
                count++;
            } else {
                return count;
            }
        }
        return count;
    }
}
