package MenuBar;

import Commands.ICommand;

import javax.swing.*;
import java.util.Objects;

/**
 * Created by hanu on 10/30/16.
 */
public class DefaultMenuItem extends JMenuItem implements IMenuItem{
    String text = null;
    private ICommand command;
    
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
    }
    
    @Override
    public void SetCommand(ICommand command){
        this.command = command;
    }
    
    public void RunCommand(){
        this.command.execute();
    }

    @Override
    public String getText(){
        return this.text;
    };

}
