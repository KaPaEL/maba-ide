package Commands;

import Editor.ITextArea;
import FileExplorer.IFileExplorer;
import TabBar.DefaultTabEditor;
import TabBar.DefaultTabSubject;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

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
    private String text = null;
    private String folderPath = ".";
    private String fileName = "";
    private ITextArea textArea;
    private IFileExplorer iFileExplorer;

    public OpenFile(RSyntaxTextArea textArea, IFileExplorer iFileExplorer) {
        this.textArea = (ITextArea) textArea;
        this.iFileExplorer = (IFileExplorer) iFileExplorer;
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
                text = readFile(selectedFile.getAbsolutePath(), StandardCharsets.UTF_8);
//                this.textArea.SetText(text);
                folderPath = fileChooser.getCurrentDirectory().getAbsolutePath();
                this.iFileExplorer.SetPath(folderPath);
                fileName = fileChooser.getSelectedFile().getName();
                this.iFileExplorer.SetFileName(fileName);
                DefaultTabEditor editor = new DefaultTabEditor(fileName);
                editor.setTextContent(text);
                editor.setFilePath(folderPath + '/');
                DefaultTabSubject.getInstance().attachObserver(editor);
                DefaultTabSubject.getInstance().setActiveTab(editor);
                DefaultTabSubject.getInstance().update();
//                System.out.println("Active tab = " + DefaultTabSubject.getInstance().getActiveTab().getTextContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Open File in Folder " + folderPath);
        System.out.println("Open File in File Name " + fileName);

    }
}
