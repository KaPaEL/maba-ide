package ToolBar;

/**
 * Created by ahmad on 11/1/2016.
 */
public interface ITool {
    String text = null;

    String getText();
    void setText(String text);

    void AddTool(ITool menu);
    void AddSeparator();
}
