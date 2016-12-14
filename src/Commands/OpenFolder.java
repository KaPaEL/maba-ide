package Commands;

import FileExplorer.FileExplorer;
import FileExplorer.IFileExplorer;
import TabBar.DefaultTabEditor;
import TabBar.DefaultTabSubject;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by hanu on 11/16/16.
 */
public class OpenFolder extends JFileChooser implements ICommand {
    private String folder=".";
    private String path;
    private IFileExplorer fileExplorer;
    private DefaultTabSubject subject;
    private DefaultTabEditor defaultTabEditor;

    public OpenFolder(FileExplorer fileExplorer) {
        this.subject = DefaultTabSubject.getInstance();
        this.fileExplorer = (IFileExplorer) fileExplorer;
    }

    @Override
    public void execute() {
        defaultTabEditor = this.subject.getActiveTab();
        defaultTabEditor.setTextContent(this.subject.getTextArea().getText());
        path = defaultTabEditor.getFilePath();

        JFileChooser fileChooser = new JFileChooser(".");
        if(path!="")
            fileChooser = new JFileChooser(path);

        fileChooser.setDialogTitle("Open Folder");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            folder = fileChooser.getSelectedFile().getAbsolutePath();
//            this.fileExplorer.SetPath(folder);
            defaultTabEditor.setFilePath(folder);
            System.out.println("Folder "+folder);
        }
        System.out.println("Open Folder "+folder);

    }
}
