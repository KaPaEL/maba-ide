package Commands;

import Editor.ITextArea;
import FileExplorer.IFileExplorer;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

/**
 * Created by HANU on 21/11/2016.
 */
public class CloseFile implements ICommand {

    public CloseFile() {
    }

    @Override
    public void execute() {
        System.out.println("File Closed");
    }
}
