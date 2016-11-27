package Commands;

import Editor.DefaultTextArea;
import Editor.ITextArea;
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
        Stack stackUndo = textArea.GetStackUndoText();
        Stack stackRedo = textArea.GetStackRedoText();
        //System.out.println("Undo :"+stackUndo.peek());
        if (stackUndo.size()>1)
        {
            stackRedo.push(stackUndo.peek());
            stackUndo.pop();
            textArea.SetText(stackUndo.peek().toString());
        }


    }
}
