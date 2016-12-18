package Commands;

import Observer.FrameObserver;
import jdk.nashorn.tools.Shell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Hanif Sudira on 12/18/2016.
 */
public class AddPlugin implements ICommand {
    private Shell shell;
    FrameObserver frameObserver;
    public  AddPlugin(FrameObserver frameObserver){
        this.frameObserver = frameObserver;
    }
    @Override
    public void execute() {
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
            }
        });
        int result = JOptionPane.showConfirmDialog(null, panel, "Add Snippet", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    }
}
