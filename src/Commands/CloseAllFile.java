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
public class CloseAllFile implements ICommand {

    public CloseAllFile() {
    }

    @Override
    public void execute() {
        while (true) {
            DefaultTabEditor closeWannabe = DefaultTabSubject.getInstance().getActiveTab();
            if (closeWannabe == null) {
                break;
            }
            DefaultTabBar.getInstance().removeTab(closeWannabe);
            closeWannabe.close();
            System.out.println("[DEBUG] File closed");
            System.out.println("[DEBUG] Number of remaining observer: " + DefaultTabSubject.getInstance().getObserverSize());
        }
        System.out.println("All File Closed");
    }
}