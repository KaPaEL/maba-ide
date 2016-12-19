package Commands;

import TabBar.DefaultTabEditor;
import TabBar.DefaultTabSubject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by HANU on 21/11/2016.
 */
public class Save  implements ICommand{
    private String content;
    private String path;
    private String fileName;
    private DefaultTabSubject subject;
    private DefaultTabEditor defaultTabEditor;

    public Save(){
        this.subject = DefaultTabSubject.getInstance();
    }

    @Override
    public void execute(){
        defaultTabEditor = this.subject.getActiveTab();
        defaultTabEditor.setTextContent(this.subject.getTextArea().getText());
        content = defaultTabEditor.getTextContent();
        path = defaultTabEditor.getFilePath();
        fileName = defaultTabEditor.getTabName();
        System.out.println("File "+path+fileName+" saving...");

            try {
                File file = new File(path+fileName);

                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.close();
                this.subject.update();
                System.out.println("File "+path+fileName+" saved");
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}
