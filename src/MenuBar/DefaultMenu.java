package MenuBar;

import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 * Created by hanu on 10/30/16.
 */
public class DefaultMenu extends JMenu implements IMenu {
    public DefaultMenu() {
        this.setName("ExampleToolMenuItem");

    }

    public DefaultMenu(String name) {
        this.setName(name);
        this.setSize(100,100);
        this.setMenuLocation(0,0);
        this.setBorder(new BevelBorder(BevelBorder.RAISED));

    }


    @Override
    public void AddMenu(IMenu menu) {
        this.add((JMenu) menu);
    }

    @Override
    public void AddSeparator() {

    }
}
