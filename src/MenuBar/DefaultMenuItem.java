package MenuBar;

import Commands.CExit;
import Commands.ICommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by hanu on 10/30/16.
 */
public class DefaultMenuItem extends JMenuItem implements IMenuItem,MouseListener{
    String text = null;
    private ICommand command;

    public DefaultMenuItem() {
        this.setName("ExampleToolMenuItem");
    }

    public DefaultMenuItem(String name){
        this.setText(name);
        this.setSize(37,20);
        addMouseListener(this);
    }


    @Override
    public void setText(String text){
        this.text = text;
    }

    @Override
    public void SetCommand(ICommand command) { this.command = command; }

    @Override
    public String getText(){
        return this.text;
    };

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.command.Execute();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
