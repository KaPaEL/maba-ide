package TabBar;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Stack;
import java.util.UUID;

/**
 * Created by winasforcepta on 11/29/2016.
 */
public class DefaultTabEditor implements ITabEditor, ITabObserver {
    private UUID tabId;
    private String tabName;
    private String textContent;
    private Stack commandUndoStack;
    private Stack commandRedoStack;
    private DefaultTabSubject subject;
    private int counter;
    private int flag;

    private int flagThread;
    
    public DefaultTabEditor(String _tabName) {
        this.subject = DefaultTabSubject.getInstance();
        this.tabId = UUID.randomUUID();
        this.commandUndoStack = new Stack();
        this.commandRedoStack = new Stack();
        this.textContent = "";
        this.counter = 0;
        this.flag = 0;
        this.flagThread = 0;
        this.tabName = _tabName;
    }

    public int getCounter() {
        return this.counter;
    }

    public void setCounter(int _counter) {
        this.counter = _counter;
    }

    public int getflag() {
        return this.flag;
    }

    public void setFlag(int _flag) {
        this.flag = _flag;
    }

<<<<<<< HEAD
=======
    public int getflagThread() {
        return this.flagThread;
    }

    public void setFlagThread(int _flagThread) {
        this.flagThread = _flagThread;
    }

>>>>>>> 87ff691b19305cf109cdfcd0dbee9377ae4a41cf
    public String getTextContent() {
        return this.textContent;
    }

    public void setTextContent(String _textContent) {
        this.textContent = _textContent;
    }

    public UUID getTabId() {
        return this.tabId;
    }

    public String getTabName() {
        return this.tabName;
    }

    public void setTabName(String _tabName) {
        this.tabName = _tabName;
    }

    public void pushCommandUndoStack(String command) {
        this.commandUndoStack.push(command);
    }

    public String peekCommandUndoStack() {
        if (!this.commandUndoStack.empty()) {
            return (String) this.commandUndoStack.peek();
        }
        return null;
    }

    public void popCommandUndoStack() {
        if (!this.commandUndoStack.empty()) {
            this.commandUndoStack.pop();
        }
    }

    public int getCommandUndoStackSize() {
        return this.commandUndoStack.size();
    }

    public boolean isCommandUndoStackEmpty() {
        return this.commandUndoStack.empty();
    }

    public void pushCommandRedoStack(String command) {
        this.commandRedoStack.push(command);
    }

    public String peekCommandRedoStack() {
        if (!this.commandRedoStack.empty()) {
            return (String) this.commandRedoStack.peek();
        }
        return null;
    }

    public void popCommandRedoStack() {
        if (!this.commandRedoStack.empty()) {
            this.commandRedoStack.pop();
        }
    }

    public boolean isCommandRedoStackEmpty() {
        return this.commandRedoStack.empty();
    }

    public int getCommandRedoStackSize() {
        return this.commandRedoStack.size();
    }

    public void update(DefaultTabEditor tabEditor) {

    }

    public void activate() {
        this.subject.setActiveTab(this);
        this.subject.update();
    }

    public void close() {
        this.subject.removeObserver(this);
        this.subject.update();
    }
    
}
