package Commands;

import Editor.ITextArea;
import TabBar.DefaultTabEditor;
import TabBar.DefaultTabSubject;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Stack;

/**
 * Created by Hanif Sudira on 11/22/2016.
 */
public class Cut implements ICommand {
    private ITextArea textArea;
    private Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
    public Cut(RSyntaxTextArea textArea){
        this.textArea = (ITextArea) textArea;
    }

    @Override
    public void execute() {
        DefaultTabEditor activeEditor = DefaultTabSubject.getInstance().getActiveTab();
        String copy = this.textArea.GetSelectedText();
        StringSelection selection = new StringSelection(copy);
        this.clipBoard.setContents(selection, selection);
        this.textArea.ReplaceRange("", this.textArea.GetSelectionStart(), this.textArea.GetSelectionEnd());
        if(!activeEditor.isCommandUndoStackEmpty() && activeEditor.peekCommandUndoStack().equals(textArea.GetText())  && activeEditor.isCommandRedoStackEmpty() )
        {
            //System.out.println("masuk cut");
            activeEditor.pushCommandUndoStack(textArea.GetText());
        }
        System.out.println("Tercut Ke Clipboard");
    }
}
