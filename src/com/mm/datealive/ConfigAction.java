package com.mm.datealive;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

import org.jetbrains.annotations.NotNull;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.IconButton;
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
public class ConfigAction extends AnAction {

    private JBPopup jBPopup;

    @Override
    public void update(@NotNull AnActionEvent event) {
        // 启用或禁用Action
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        JBPopupFactory instance = JBPopupFactory.getInstance();
        JBPopup jBPopupTable = instance.createComponentPopupBuilder(new TableUI(project).getComponent(), null)
                .setTitle("config")
                .setMovable(true)
                .setResizable(false)
                .setFocusable(true)
                .setMinSize(new Dimension(280,300)).setCancelOnClickOutside(false).setCancelButton(new IconButton("close", AllIcons.Actions.Cancel))
                .createPopup();
        jBPopupTable.setRequestFocus(true);
        jBPopupTable.showCenteredInCurrentWindow(project);
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
