package Commands;

import Editor.ITextArea;
import FileExplorer.IFileExplorer;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

/**
 * Created by HANU on 21/11/2016.
 */
public class CloseAllFile implements ICommand {

    public CloseAllFile() {
    }

    @Override
    public void execute() {
        System.out.println("All File Closed");
    }
}