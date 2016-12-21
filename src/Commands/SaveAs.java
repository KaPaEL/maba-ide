package Commands;

import TabBar.DefaultTabBar;
import TabBar.DefaultTabEditor;
import TabBar.DefaultTabSubject;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by HANU on 21/11/2016.
 */
public class SaveAs extends JFileChooser implements ICommand {
    private String content;
    private String path;
    private String fileName;
    private String newFileName;
    private String newPath;
    private DefaultTabSubject subject;
    private DefaultTabEditor defaultTabEditor;


    public SaveAs() {
        this.subject = DefaultTabSubject.getInstance();
    }

    @Override
    public void execute() {
        defaultTabEditor = this.subject.getActiveTab();
        defaultTabEditor.setTextContent(this.subject.getTextArea().getText());
        content = defaultTabEditor.getTextContent();
        path = defaultTabEditor.getFilePath();
        fileName = defaultTabEditor.getTabName();

        JFileChooser fileChooser = new JFileChooser(".");
        if(path!="")
            fileChooser = new JFileChooser(path);

        fileChooser.setDialogTitle("Save As");
        fileChooser.setApproveButtonText("Save");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".c", "c", "c");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                file = new File(file.toString() + ".c");
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.close();
                newPath = fileChooser.getSelectedFile().getParentFile().getAbsolutePath();
                newFileName = fileChooser.getSelectedFile().getName();
                DefaultTabEditor tabEditor = new DefaultTabEditor(newFileName + ".c");
                tabEditor.setTextContent(content);
                tabEditor.setFilePath(newPath);
                System.out.println("File " + newPath);
                DefaultTabBar.getInstance().addTab(tabEditor);
                this.subject.attachObserver(tabEditor);
                this.subject.setActiveTab(tabEditor);
                this.subject.update();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("File " + newPath + newFileName + ".c" + " saved");
    }
}
