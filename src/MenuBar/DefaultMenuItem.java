package MenuBar;

import javax.swing.*;

/**
 * Created by hanu on 10/30/16.
 */
public class DefaultMenuItem extends JMenuItem implements IMenuItem{
    String text = null;

    public DefaultMenuItem() {
        this.setName("ExampleToolMenuItem");
    }

    public DefaultMenuItem(String name){
        this.setText(name);
        this.setSize(37,20);
    }

    @Override
    public void setText(String text){
        this.text = text;
    };

    @Override
    public String getText(){
        return this.text;
    };

}
