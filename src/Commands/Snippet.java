package Commands;

import Editor.ITextArea;
import TabBar.DefaultTabEditor;
import TabBar.DefaultTabSubject;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

import static Commands.OpenFile.readFile;

/**
 * Created by Hanif Sudira on 12/6/2016.
 */
public class Snippet implements ICommand {
    private ITextArea textArea;
    private String snippet;

    public Snippet(RSyntaxTextArea textArea, String snippet){
        this.textArea = (ITextArea) textArea;
        this.snippet = snippet;
    }

    @Override
    public void execute() {
        DefaultTabEditor activeEditor = DefaultTabSubject.getInstance().getActiveTab();
        try{
            String filename = "assets/snippet/"+snippet+".txt";
            String file = readFile(filename, Charset.defaultCharset());
            this.textArea.ReplaceRange(file, this.textArea.GetSelectionStart(), this.textArea.GetSelectionEnd());
            if(!activeEditor.isCommandUndoStackEmpty() && activeEditor.peekCommandUndoStack().equals(textArea.GetText())  && activeEditor.isCommandRedoStackEmpty() )
            {
                activeEditor.pushCommandUndoStack(textArea.GetText());
                DefaultTabSubject.getInstance().getActiveTab().setCounter(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
