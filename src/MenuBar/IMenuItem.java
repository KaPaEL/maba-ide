package MenuBar;

import Commands.CExit;
import Commands.ICommand;

/**
 * Created by Hanif Sudira on 10/30/2016.
 */
public interface IMenuItem {

    String text = null;

    String getText();
    void setText(String text);
    void SetCommand(ICommand command);
}
