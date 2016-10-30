package MenuBar;

/**
 * Created by Hanif Sudira on 10/30/2016.
 */
public interface IMenuItem {

    String text = null;

    String getText();
    void setText(String text);

    void AddMenuItem(IMenuItem menuItem);
    void AddSeparator();
    //void SetCommand(ICommand command);
}
