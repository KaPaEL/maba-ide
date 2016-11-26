package Commands;

import Editor.ITextArea;
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
        Stack stackUndo = textArea.GetStackUndoText();
        Stack stackRedo = textArea.GetStackRedoText();
        //System.out.println("Redo :"+stackRedo.peek());
        textArea.SetText(stackRedo.peek().toString());
        if (stackRedo.size() > 1)
        {
            stackUndo.push(stackRedo.peek());
            stackRedo.pop();
        }

    }
}
