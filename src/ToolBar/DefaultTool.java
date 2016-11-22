package ToolBar;

import Commands.ICommand;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by ahmad on 11/1/2016.
 */
public class DefaultTool extends JButton implements ITool,MouseListener {
    private ICommand command;

    public DefaultTool(String imagePath, String toolTip){
        ImageIcon imageIcon = new ImageIcon(imagePath);
        this.setIcon(imageIcon);
        this.setBorderPainted(false);
        this.setToolTipText(toolTip);
        addMouseListener(this);
    }

    @Override
    public String GetText() {
        return null;
    }

    @Override
    public void SetText(String text) {

    }

    @Override
    public void SetCommand(ICommand command) { this.command = command; }

    @Override
    public void SetMnemonic(char mnemonic) {
        this.setMnemonic(mnemonic);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.command.execute();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
