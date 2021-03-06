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
    private IFileExplorer fileExplorer;

    public OpenFolder(FileExplorer fileExplorer) {
        this.fileExplorer = (IFileExplorer) fileExplorer;
    }

    @Override
    public void execute() {
        JFileChooser fileChooser = new JFileChooser(".");

        fileChooser.setDialogTitle("Open Folder");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            folder = fileChooser.getSelectedFile().getAbsolutePath();
            this.fileExplorer.update(folder);
            System.out.println("Folder "+folder);
        }
        System.out.println("Open Folder "+folder);

    }
}
