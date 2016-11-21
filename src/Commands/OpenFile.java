package Commands;

import Editor.ITextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
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
public class OpenFile extends JFileChooser implements ICommand{
    private String text=null;
    private ITextArea textArea;

    public OpenFile(RSyntaxTextArea textArea) {
        this.textArea = (ITextArea) textArea;
    }

    static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    @Override
    public void execute() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String coba = selectedFile.getAbsolutePath();
            System.out.println(coba);
            try {
                text = readFile(selectedFile.getAbsolutePath(), StandardCharsets.UTF_8);
                this.textArea.SetText(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Open file");

    }

}

