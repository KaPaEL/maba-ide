package Editor;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 * Created by hanu on 11/16/16.
 */
public class DefaultTextArea extends RSyntaxTextArea implements ITextArea {

    public DefaultTextArea() {
        this.setSize(20,60);
        this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        this.setCodeFoldingEnabled(true);
        this.setAutoscrolls(true);
        this.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                DefaultTextArea editArea = (DefaultTextArea)e.getSource();
                int linenum = 1;
                int columnnum = 1;
                try {
                    int caretpos = editArea.getCaretPosition();
                    linenum = editArea.getLineOfOffset(caretpos);
                    columnnum = caretpos - editArea.getLineStartOffset(linenum);
                    linenum += 1;
                }
                catch(Exception ex) { }
                updateStatus(linenum, columnnum);
            }
        });
    }

    @Override
    public void SetText(String text) {
        this.setText(text);
    }

    @Override
    public String GetText() { return this.getText(); }

    @Override
    public String GetSelectedText() { return this.getSelectedText(); }

    @Override
    public int GetSelectionStart() { return this.getSelectionStart(); }

    @Override
    public int GetSelectionEnd() { return this.getSelectionEnd(); }

    @Override
    public void ReplaceRange( String replace, int start, int end) { this.replaceRange(replace, start, end); }

    @Override
    public void SelectAll() { this.selectAll(); }

    private void updateStatus(int linenumber, int columnnumber) {
        System.out.println("Line: " + linenumber + " Column: " + columnnumber);
    }

}
