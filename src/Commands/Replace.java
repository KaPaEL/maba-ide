package Commands;

import Editor.ITextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Hanif Sudira on 11/23/2016.
 */
public class Replace implements ICommand {
    private ITextArea textArea;
    private JPanel jPanel;
    private JSplitPane jSplit;

    public Replace(RSyntaxTextArea textArea, JPanel jPanel,JSplitPane jSplit2){
        this.textArea = (ITextArea)textArea;
        this.jPanel = jPanel;
        this.jSplit = jSplit2;
    }

    @Override
    public void execute() {
        System.out.println("\n");
        System.out.println("Masuk Bos");
        //this.jPanel.setVisible(true);
        this.jSplit.getBottomComponent().setVisible(true);
        this.jSplit.setDividerLocation(0.1);

        JButton findButton = ((JButton)this.jPanel.getComponent(1));
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String findText = ((JTextField)Replace.this.jPanel.getComponent(0)).getText().toString().trim();
                if(!findText.equals("")){
                    try {
                        findText = findText.toLowerCase();
                        Highlighter highlighter = Replace.this.textArea.GetHighlighter();
                        highlighter.removeAllHighlights();
                        Document document = Replace.this.textArea.GetDocument();
                        String allTextFile = document.getText(0,document.getLength());
                        allTextFile = allTextFile.toLowerCase();
                        for (int i = -1; (i = allTextFile.indexOf(findText, i + 1)) != -1; ) {
                            highlighter.addHighlight(i,i+findText.length(),new DefaultHighlighter.DefaultHighlightPainter(Color.cyan));
                        }

                    } catch (BadLocationException e1) {
                        e1.printStackTrace();
                    }
                }
                System.out.println("Kosong");
            }
        });

        JButton replaceButton = ((JButton)this.jPanel.getComponent(3));
        replaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String findText = ((JTextField)Replace.this.jPanel.getComponent(0)).getText().toString().trim();
                String replaceText = ((JTextField)Replace.this.jPanel.getComponent(2)).getText().toString().trim();
                if(!findText.equals(replaceText)){
                    Replace.this.textArea.SetText(Replace.this.textArea.GetText().replaceAll(findText,replaceText));
                }
                System.out.println("Sama");
            }
        });
    }
}
