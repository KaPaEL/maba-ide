package FileExplorer;

import Commands.ICommand;
import TabBar.DefaultTabEditor;
import TabBar.DefaultTabSubject;

import javax.swing.*;
import java.io.File;

/**
 * Created by hanu on 11/16/16.
 */
public class DefaultFileExplorer extends FileExplorer implements IFileExplorer {

    private String path = ".";
    /**
     * Construct a FileTree
     *
     * @param folderPath
     */
    public DefaultFileExplorer(String folderPath) {
        super(folderPath);
        this.path=folderPath;
    }

    @Override
    public void update(String path) {
        if(path=="")
            path=".";
        System.out.println(path);
        this.reloadTree(path);
    }
}
