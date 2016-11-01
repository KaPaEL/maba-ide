package ToolBar;

import javax.swing.*;

/**
 * Created by ahmad on 11/1/2016.
 */
public class DefaultToolItem extends JButton implements IToolBarItem {
    public DefaultToolItem(){
        this.setName("ExampleToolBarItem");
        this.setSize(37,20);
    }
    public DefaultToolItem(String name){
        this.setName(name);
    }

    @Override
    public void AddToolBarItem(IToolBarItem toolBarItem) {

    }

    @Override
    public void AddSeparator() {

    }
}
