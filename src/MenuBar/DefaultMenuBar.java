package MenuBar;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by Hanif Sudira on 10/30/2016.
 */
public class DefaultMenuBar extends JMenuBar implements IMenuBar {

    public DefaultMenuBar(){
        this.setSize(300,50);
        this.setBackground(Color.WHITE);
        this.setLocation(0,0);
        this.setBorder(new BevelBorder(BevelBorder.RAISED));
    }


    @Override
    public void AddMenu(IMenu menu) {
        this.add((JMenu) menu);
    }
}
