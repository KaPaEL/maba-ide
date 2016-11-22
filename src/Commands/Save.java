package Commands;

import Editor.ITextArea;
import FileExplorer.IFileExplorer;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by HANU on 21/11/2016.
 */
public class Save  implements ICommand {
    private IFileExplorer iFileExplorer;
    private ITextArea textArea;

    public Save(RSyntaxTextArea textArea,  IFileExplorer iFileExplorer) {
        this.textArea = (ITextArea) textArea;
        this.iFileExplorer = iFileExplorer;
    }

    @Override
    public void execute(){
        if(this.iFileExplorer.GetPath()!="." ||  this.iFileExplorer.GetFileName()!="null"){
            try {
                File file = new File(this.iFileExplorer.GetPath()+"\\"+ this.iFileExplorer.GetFileName());

                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(this.textArea.GetText());
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("File "+this.iFileExplorer.GetPath()+"\\"+this.iFileExplorer.GetFileName()+".c"+" saved");
        }
    }

}
