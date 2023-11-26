package com.mm.datealive;

import javax.imageio.ImageIO;
import javax.swing.*;

import a.e.I;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.IdeaPluginDescriptorImpl;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.IconLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

public class IconConstants {

    public static final Icon FUNCTION_OBJ_ICON = IconLoader.getIcon("/icons/obj.png", IconConstants.class);
    public static final Icon FUNCTION_STDFIELDFILE_ICON =
        IconLoader.getIcon("/icons/stdFieldFile.png", IconConstants.class);
    public static final Icon[] DATEALIVE_ICON = new Icon[10];

    public static final ImageIcon[] DATEALIVE_IMAGEICON = new ImageIcon[10];

    //public static final ImageIcon[] DATEALIVE_ICONIMG = new ImageIcon[10];

    static {
        /*for (int i = 0; i < 10; i++) {
            DATEALIVE_ICON[i] = IconLoader.getIcon("/icons/" + i + ".png", IconConstants.class);
        }*/

        // 获取jar包中类路径
        String jarPath = IconConstants.class.getResource("IconConstants.class").toString();
        jarPath = jarPath.substring(0, jarPath.lastIndexOf("com"));
        //Messages.showMessageDialog(IconConstants.class.getResource("IconConstants.class").getPath(), "提示", Messages.getInformationIcon());
        for (int i = 0; i < 10; i++) {
            try {
                URL url = new URL(jarPath + "icons/" + i + ".png");
                InputStream is = url.openStream();
                BufferedImage bufferedImage = ImageIO.read(is);
                ImageIcon icon = new ImageIcon(bufferedImage);
                DATEALIVE_ICON[i] = icon;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        /*List<? extends IdeaPluginDescriptor> loadedPlugins = PluginManager.getLoadedPlugins();
        String path = "";
        for (IdeaPluginDescriptor item : loadedPlugins) {
            IdeaPluginDescriptorImpl ideaPluginDescriptor =  (IdeaPluginDescriptorImpl)item;
            if (ideaPluginDescriptor.getName().equals("datealive")) {
                Path pluginPath = ideaPluginDescriptor.getPluginPath();
                path = pluginPath.toString();

                Messages.showMessageDialog(IconConstants.class.getClassLoader().getResource("MyAction.class").toString(), "提示", Messages.getInformationIcon());
                Messages.showMessageDialog(IconConstants.class.getClassLoader().getResource("1.png").toString(), "提示", Messages.getInformationIcon());
            }
        }*/
    }

}
