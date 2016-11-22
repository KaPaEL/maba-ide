package Commands;

import Editor.ITextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * Created by Hanif Sudira on 11/22/2016.
 */
public class Copy implements ICommand {
    private ITextArea textArea;
    private Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    public Copy(RSyntaxTextArea textArea) {
        this.textArea = (ITextArea) textArea;
    }

    @Override
    public void execute() {
        String copy = this.textArea.GetSelectedText();
        StringSelection selection = new StringSelection(copy);
        this.clipboard.setContents(selection,selection);
        System.out.println("Tercopy Ke Clipboard");
    }
}
