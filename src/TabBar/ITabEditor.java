package TabBar;

import java.util.Stack;
import java.util.UUID;

/**
 * Created by winasforcepta on 11/29/2016.
 */
public interface ITabEditor {

    UUID getTabId();
    String getTabName();
    void setTabName(String _tabName);

    void pushCommandUndoStack(String command);
    String peekCommandUndoStack();
    void popCommandUndoStack();

    void pushCommandRedoStack(String command);
    String peekCommandRedoStack();
    void popCommandRedoStack();
}
