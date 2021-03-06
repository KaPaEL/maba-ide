package Commands;

import Editor.DefaultTextArea;
import Editor.ITextArea;
import TabBar.DefaultTabEditor;
import TabBar.DefaultTabSubject;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import java.util.Stack;

/**
 * Created by ALPRO on 23/11/2016.
 */
public class Undo implements ICommand {

    private ITextArea textArea;
    public Undo (RSyntaxTextArea textArea){
        this.textArea = (ITextArea) textArea;
    }
    @Override
    public void execute() {
        //System.out.println("Undo :"+ DefaultTabSubject.getInstance().getActiveTab().peekCommandUndoStack());
        DefaultTabEditor activedEditor = DefaultTabSubject.getInstance().getActiveTab();
        activedEditor.setTextContent(DefaultTabSubject.getInstance().getTextArea().GetText());
        if (activedEditor.getCommandUndoStackSize() > 1)
        {
            /*if(!activedEditor.peekCommandRedoStack().equals(activedEditor.getTextContent()))
            {
                activedEditor.pushCommandRedoStack(activedEditor.getTextContent());
            }*/
            //System.out.println("Latest :"+ );
            String lastUndoCommand = activedEditor.peekCommandUndoStack();
            activedEditor.pushCommandRedoStack(lastUndoCommand);
            activedEditor.popCommandUndoStack();
            textArea.SetText(activedEditor.peekCommandUndoStack().toString());
        }


    }
}
