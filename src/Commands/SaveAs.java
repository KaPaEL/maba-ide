package Commands;

import Editor.ITextArea;
import FileExplorer.IFileExplorer;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by HANU on 21/11/2016.
 */
public class SaveAs extends JFileChooser implements ICommand {
    private String text;
    private String folderPath;
    private String fileName;
    private IFileExplorer iFileExplorer;
    private ITextArea textArea;

    public SaveAs(RSyntaxTextArea textArea, IFileExplorer iFileExplorer) {
        this.textArea = (ITextArea) textArea;
        this.iFileExplorer = iFileExplorer;
    }

    @Override
    public void execute() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setApproveButtonText("Save");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".c", "c", "c");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {

            try {
            File file = fileChooser.getSelectedFile();

                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(this.textArea.GetText());
                bw.close();
                folderPath = fileChooser.getSelectedFile().getAbsolutePath();
                fileName = fileChooser.getSelectedFile().getName();
                this.iFileExplorer.SetPath(folderPath);
                this.iFileExplorer.SetFileName(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("File "+folderPath+"\\"+fileName+".c"+" saved");
    }
}
