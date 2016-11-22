package Commands;

import Editor.ITextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

/**
 * Created by Hanif Sudira on 11/22/2016.
 */
public class SelectAll implements ICommand {
    private ITextArea textArea;

    public SelectAll(RSyntaxTextArea textArea){
        this.textArea = (ITextArea) textArea;
    }

    @Override
    public void execute() {
        this.textArea.SelectAll();
        System.out.println("Select All");
    }
}
