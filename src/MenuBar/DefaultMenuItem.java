package MenuBar;

import javax.swing.*;

/**
 * Created by hanu on 10/30/16.
 */
public class DefaultMenuItem extends JMenuItem implements IMenuItem{

    public DefaultMenuItem() {
        this.setName("ExampleToolMenuItem");
        this.setSize(37,20);

    }

    public DefaultMenuItem(String name){
        this.setName(name);
    }

    @Override

    public void AddMenuItem(IMenuItem menuItem) {

    }

    @Override
    public void AddSeparator() {

    }
}
