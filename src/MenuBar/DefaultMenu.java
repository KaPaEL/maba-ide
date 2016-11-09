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
        int x=this.getLocation().x;
        int y=this.getLocation().y;
        y=y+this.getPreferredSize().height;
        this.setMenuLocation(x,y);

        this.setBorder(new BevelBorder(BevelBorder.RAISED));
    }


    @Override
    public void AddMenuItem(IMenuItem menu) {
        this.add((JMenuItem) menu);
    }

    @Override
    public void AddSeparator() {
        this.add( new JSeparator());
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
