package MenuBar;

/**
 * Created by hanu on 10/30/16.
 */
public interface IMenu {
    String text = null;

    String getText();
    void setText(String text);

    void AddMenu(IMenu menu);
//    void AddSeparator();

}
