package Commands;

import TabBar.DefaultTabBar;
import TabBar.DefaultTabEditor;
import TabBar.DefaultTabSubject;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by mfrazi on 15/11/2016.
 */
public class NewFile implements ICommand{
    private DefaultTabSubject subject;
    private DefaultTabEditor defaultTabEditor;

    public NewFile() {
        this.subject = DefaultTabSubject.getInstance();
    }

    @Override
    public void execute(){
        String fileName = JOptionPane.showInputDialog(null,
                "Masukkan Nama File ?",
                "Dialog",
                JOptionPane.QUESTION_MESSAGE);
        DefaultTabEditor tabEditor = new DefaultTabEditor(fileName + ".c");

        DefaultTabBar.getInstance().addTab(tabEditor);
        this.subject.attachObserver(tabEditor);
        this.subject.setActiveTab(tabEditor);
        this.subject.update();

        System.out.println("[DEBUG TAB NAME] " + tabEditor.getTabName());
        System.out.println("[DEBUG TAB FILE] " + tabEditor.getFilePath());

        System.out.println("[DEBUG] " + DefaultTabSubject.getInstance().getActiveTab().getTabId().toString());
    }
}
