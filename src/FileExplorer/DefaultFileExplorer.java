package FileExplorer;

import Commands.ICommand;

import javax.swing.*;
import java.io.File;

/**
 * Created by hanu on 11/16/16.
 */
public class DefaultFileExplorer extends FileExplorer implements IFileExplorer {

    private String folderPath = ".";

    /**
     * Construct a FileTree
     *
     * @param folderPath
     */
    public DefaultFileExplorer(String folderPath) {
        super(folderPath);
        this.folderPath=folderPath;
    }

    @Override
    public void SetPath(String folderPath) {
        this.folderPath=folderPath;
    }


}
