package MenuBar;

import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 * Created by hanu on 10/30/16.
 */
public class DefaultMenu extends JMenu implements IMenu {
    String text = null;

    public DefaultMenu() {
        this.setName("ExampleToolMenuItem");

    }

    public DefaultMenu(String name) {
        this.setText(name);
        this.setSize(200,200);
        this.setMenuLocation(0,0);
        this.setBorder(new BevelBorder(BevelBorder.RAISED));
    }


    @Override
    public void AddMenu(IMenu menu) {
        this.add((JMenu) menu);
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
