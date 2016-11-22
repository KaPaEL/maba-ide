package MenuBar;

import Commands.ICommand;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by hanu on 10/30/16.
 */
public class DefaultMenuItem extends JMenuItem implements IMenuItem,MouseListener,ActionListener{
    String text = null;
    private ICommand command;
    
    public DefaultMenuItem(){
        this.setName("ExampleToolMenuItem");
    }
    
    public DefaultMenuItem(String name){
        this.SetText(name);
        this.setSize(37, 20);
        addMouseListener(this);
        this.setBorderPainted(false);
        addActionListener(this);
    }
    
    
    @Override
    public void SetText(String text){
        this.text = text;
    }
    
    @Override
    public void SetCommand(ICommand command){
        this.command = command;
    }
    
    
    @Override
    public String getText(){
        return this.text;
    }
    
    ;
    
    @Override
    public void mouseClicked(MouseEvent e){
        
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
//        this.command.execute();
    }
    
    @Override
    public void mouseEntered(MouseEvent e){
        
    }
    
    @Override
    public void mouseExited(MouseEvent e){
        
    }
    
    public void SetIcon(ImageIcon icon){
        this.setIcon(icon);
    }

    @Override
    public void SetAcceleration(KeyStroke keyStroke) {
        this.setAccelerator(keyStroke);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.command.execute();
    }
}
