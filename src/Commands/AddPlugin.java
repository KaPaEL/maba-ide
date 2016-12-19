package Commands;

import Observer.FrameObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by Hanif Sudira on 12/18/2016.
 */
public class AddPlugin implements ICommand {
    String temp;
    FrameObserver frameObserver;
    public  AddPlugin(FrameObserver frameObserver){
        this.frameObserver = frameObserver;
    }
    @Override
    public void execute() throws FileNotFoundException {
        JPanel panel = new JPanel(new GridLayout(0,1));
        JTextField fieldNameSnippet = new JTextField();
        panel.add(new JLabel("Snippet Name"));
        panel.add(fieldNameSnippet);
        panel.add(new JLabel("Code"));
        Button showFileDialogButton = new Button("Choose File");
        panel.add(showFileDialogButton);
        panel.add(new JLabel(""));
        Label status = new Label("File Selected : -");
        panel.add(status);
        final FileDialog fileDialog = new FileDialog(frameObserver,"Select file");
        showFileDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileDialog.setVisible(true);
                status.setText("File Selected :" + fileDialog.getDirectory() + fileDialog.getFile());
                temp = fileDialog.getDirectory() + fileDialog.getFile();
            }
        });
        int result = JOptionPane.showConfirmDialog(null, panel, "Add Snippet", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(result == JOptionPane.OK_OPTION){
            try{
                File file = new File(temp);
                FileInputStream inputStream = new FileInputStream(file);
                FileOutputStream outputStream = new FileOutputStream("assets/snippet/"+file.getName());
                byte[] buffer = new byte[2048];
                int length;
                while((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer,0,length);
                }
                inputStream.close();
                outputStream.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
