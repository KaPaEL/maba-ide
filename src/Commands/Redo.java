package Commands;

import Editor.ITextArea;
import TabBar.DefaultTabEditor;
import TabBar.DefaultTabSubject;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import java.util.Stack;

/**
 * Created by ALPRO on 26/11/2016.
 */
public class Redo implements ICommand {
    private ITextArea textArea;
    public Redo (RSyntaxTextArea textArea){
        this.textArea = (ITextArea) textArea;
    }
    @Override
    public void execute() {
        DefaultTabEditor activeTab = DefaultTabSubject.getInstance().getActiveTab();
        //System.out.println("Redo :"+stackRedo.peek());
        if(!activeTab.isCommandRedoStackEmpty())
        {
            textArea.SetText(activeTab.peekCommandRedoStack().toString());
            activeTab.pushCommandUndoStack(activeTab.peekCommandRedoStack());
            activeTab.popCommandRedoStack();
        }

    }
}
