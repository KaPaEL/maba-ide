package ToolBar;

import javax.swing.*;

/**
 * Created by ahmad on 11/16/2016.
 */
public class MainToolBar extends JToolBar {
    private IToolBar iToolBar;
    public MainToolBar(){

        this.iToolBar = new DefaultToolBar();
        DefaultTool newTool = new DefaultTool("assets/new.png","New file");
        this.iToolBar.AddToolItem(newTool);
    }

}
