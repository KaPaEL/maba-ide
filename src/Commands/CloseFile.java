package Commands;

import Editor.ITextArea;
import FileExplorer.IFileExplorer;
import TabBar.DefaultTabBar;
import TabBar.DefaultTabEditor;
import TabBar.DefaultTabSubject;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

/**
 * Created by HANU on 21/11/2016.
 */
public class CloseFile implements ICommand {

    public CloseFile() {
    }

    @Override
    public void execute() {
        DefaultTabEditor closeWannabe = DefaultTabSubject.getInstance().getActiveTab();
        DefaultTabBar.getInstance().removeTab(closeWannabe);
        closeWannabe.close();
        System.out.println("[DEBUG] File closed");
    }
}
