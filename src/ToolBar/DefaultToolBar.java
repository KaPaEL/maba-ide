package ToolBar;


/**
 * Created by ahmad on 10/30/2016.
 */
import javax.swing.*;

public class DefaultToolBar extends JToolBar implements IToolBar {

    public DefaultToolBar(){
        this.setName("ExampleToolMenuItem");
        this.setSize(37, 20);
    }

    @Override
    public void AddToolItem(ITool toolItem) {
        this.add((JButton) toolItem);

    }
}
