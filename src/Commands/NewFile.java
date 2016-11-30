package Commands;

import Editor.ITextArea;
import FileExplorer.IFileExplorer;
import TabBar.DefaultTabBar;
import TabBar.DefaultTabEditor;
import TabBar.DefaultTabSubject;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by mfrazi on 15/11/2016.
 */
public class NewFile implements ICommand{
    private IFileExplorer iFileExplorer;
    private String folderPath;
    private JFrame mainwindow;
    private ITextArea textArea;

    public NewFile(RSyntaxTextArea textArea, IFileExplorer iFileExplorer, JFrame mainwindow) {
//        this.textArea = (ITextArea) textArea;
        this.iFileExplorer = iFileExplorer;
        this.mainwindow = mainwindow;
    }

    @Override
    public void execute(){
        String fileName = JOptionPane.showInputDialog(null,
                "Masukkan Nama File ?",
                "Dialog",
                JOptionPane.QUESTION_MESSAGE);
        try {
            String content = "";
//            this.textArea.SetText("");
            File file = new File(this.iFileExplorer.GetPath()+"\\"+fileName+".c");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        iFileExplorer.SetFileName(fileName+".c");
        System.out.println("File "+this.iFileExplorer.GetPath()+"\\"+fileName+".c"+" created");
        DefaultTabEditor tabEditor = new DefaultTabEditor(fileName + ".c");
        DefaultTabSubject.getInstance().attachObserver(tabEditor);
        DefaultTabSubject.getInstance().setActiveTab(tabEditor);
        DefaultTabBar.getInstance().addTab(tabEditor);
        System.out.println("[DEBUG] " + DefaultTabSubject.getInstance().getActiveTab().getTabId().toString());
    }
}
