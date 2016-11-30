package Commands;

import Editor.DefaultTextArea;
import Editor.ITextArea;
import TabBar.DefaultTabEditor;
import TabBar.DefaultTabSubject;
import ToolBar.DefaultTool;

/**
 * Created by ALPRO on 25/11/2016.
 */
public class CheckUndo implements ICommand {
    private ITextArea textArea;
    private DefaultTool undoTool;

    public CheckUndo(DefaultTool defaultTool, DefaultTextArea defaultTextArea){
        this.textArea = defaultTextArea;
        this.undoTool = defaultTool;
    }
    @Override
    public void execute() {
        DefaultTabEditor activeTab = DefaultTabSubject.getInstance().getActiveTab();
        if(activeTab.isCommandUndoStackEmpty()){
            this.undoTool.setEnabled(false);
        }
        else {
            this.undoTool.setEnabled(true);
        }

    }
}
