package Commands;

import Editor.ITextArea;
import TabBar.DefaultTabEditor;
import TabBar.DefaultTabSubject;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Stack;

/**
 * Created by Hanif Sudira on 11/22/2016.
 */
public class Paste implements ICommand {
    private ITextArea textArea;
    private Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();

    public Paste(RSyntaxTextArea textArea){
        this.textArea = (ITextArea) textArea;
    }
    @Override
    public void execute() {
        DefaultTabEditor activeEditor = DefaultTabSubject.getInstance().getActiveTab();
        Transferable clipPaste = clipBoard.getContents(this.textArea);
        try{
            String paste = (String)clipPaste.getTransferData(DataFlavor.stringFlavor);
            this.textArea.ReplaceRange(paste, this.textArea.GetSelectionStart(), this.textArea.GetSelectionEnd());
            if(!activeEditor.isCommandUndoStackEmpty() && activeEditor.peekCommandUndoStack().equals(textArea.GetText())  && activeEditor.isCommandRedoStackEmpty() )
            {
                activeEditor.pushCommandUndoStack(textArea.GetText());
                DefaultTabSubject.getInstance().getActiveTab().setCounter(0);
            }
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Terpaste Ke Editor");
    }
}
