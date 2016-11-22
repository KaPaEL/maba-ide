package MenuBar;

import Commands.ICommand;

import javax.swing.*;

/**
 * Created by Hanif Sudira on 10/30/2016.
 */
public interface IMenuItem {
    String text = null;
    String getText();

    void SetText(String text);
    void SetCommand(ICommand command);
    void SetIcon(ImageIcon icon);
    void SetAcceleration(KeyStroke keyStroke);
}
