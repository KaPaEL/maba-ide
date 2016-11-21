package Commands;

import Editor.ITextArea;
import FileExplorer.IFileExplorer;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

/**
 * Created by HANU on 21/11/2016.
 */
public class CloseFile implements ICommand {
    private IFileExplorer iFileExplorer;
    private ITextArea textArea;

    public CloseFile(RSyntaxTextArea textArea, IFileExplorer iFileExplorer) {
        this.textArea = (ITextArea) textArea;
        this.iFileExplorer = iFileExplorer;
    }

    @Override
    public void execute() {
        this.iFileExplorer.SetFileName("");
        this.textArea.SetText("");
        System.out.println("File Closed");
    }
}
