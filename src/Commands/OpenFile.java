package Commands;

import FileExplorer.FileExplorer;
import FileExplorer.IFileExplorer;
import TabBar.DefaultTabBar;
import TabBar.DefaultTabEditor;
import TabBar.DefaultTabSubject;

import javax.swing.*;

import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by mfrazi on 15/11/2016.
 */
// TODO: 15/11/2016 http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/FileChooserDemoProject/src/components/FileChooserDemo.java
public class OpenFile extends JFileChooser implements ICommand {
    private String content;
    private String newPath;
    private String newFileName;
    private DefaultTabSubject subject;
    private IFileExplorer fileExplorer;

    public OpenFile(FileExplorer fileExplorer) {
        this.subject = DefaultTabSubject.getInstance();
        this.fileExplorer = (IFileExplorer) fileExplorer;
    }

    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    @Override
    public void execute() {
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setDialogTitle("Open File");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".c", "c", "c");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String coba = selectedFile.getAbsolutePath();
            System.out.println(coba);
            try {
                content = readFile(selectedFile.getAbsolutePath(), StandardCharsets.UTF_8);
                newPath = fileChooser.getCurrentDirectory().getAbsolutePath();
                newFileName = fileChooser.getSelectedFile().getName();
                DefaultTabEditor editor = new DefaultTabEditor(newFileName);
                editor.setTextContent(content);
                editor.setFilePath(newPath + '\\');
                this.fileExplorer.update(newPath + '\\');
                this.subject.attachObserver(editor);
                this.subject.setActiveTab(editor);
                this.subject.update();
                DefaultTabBar.getInstance().addTab(editor);
//                System.out.println("Active tab = " + DefaultTabSubject.getInstance().getActiveTab().getTextContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Open File in Folder " + newPath);
        System.out.println("Open File in File Name " + newFileName);

    }
}
