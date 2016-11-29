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
 * Created by Hanif Sudira on 11/22/2016.
 */
public class Find implements ICommand {
    private ITextArea textArea;
    private JPanel jPanel;
    private  JSplitPane jSplit;

    public Find(RSyntaxTextArea textArea, JPanel jPanel,JSplitPane jSplit){
        this.textArea = (ITextArea)textArea;
        this.jPanel = jPanel;
        this.jSplit = jSplit;
    }

    @Override
    public void execute() {

        this.jSplit.getBottomComponent().setVisible(true);
        this.jSplit.setDividerLocation(0.1);

        JButton findButton = ((JButton)this.jPanel.getComponent(1));
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String findText = ((JTextField)Find.this.jPanel.getComponent(0)).getText().toString().trim();
                if(!findText.equals("")){
                    try {
                        findText = findText.toLowerCase();
                        Highlighter highlighter = Find.this.textArea.GetHighlighter();
                        highlighter.removeAllHighlights();
                        Document document = Find.this.textArea.GetDocument();
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
    }
}
