package ToolBar;

/**
 * Created by ahmad on 10/30/2016.
 */
public interface IToolBarItem {
    String text = null;

    String getText();
    void setText(String text);

    void AddToolBarItem(IToolBarItem toolBarItem);
    void AddSeparator();

}
