package Commands;

import Editor.ITextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

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
        Transferable clipPaste = clipBoard.getContents(this.textArea);
        try{
            String paste = (String)clipPaste.getTransferData(DataFlavor.stringFlavor);
            this.textArea.ReplaceRange(paste, this.textArea.GetSelectionStart(), this.textArea.GetSelectionEnd());
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Terpaste Ke Editor");
    }
}
