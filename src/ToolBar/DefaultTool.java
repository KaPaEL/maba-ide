package ToolBar;

import javax.swing.*;

/**
 * Created by ahmad on 11/1/2016.
 */
public class DefaultTool extends JButton implements ITool{
    public DefaultTool(){


    }

    public DefaultTool(String imagePath, String toolTip){
        ImageIcon imageIcon = new ImageIcon(imagePath);
        this.setIcon(imageIcon);
        this.setToolTipText("testing");

    }


    @Override
    public void AddTool(ITool menu) {
        this.add((JButton) menu);
    }

    @Override
    public void AddSeparator() {

    }
}
