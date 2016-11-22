package Editor;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import java.awt.*;

/**
 * Created by hanu on 11/16/16.
 */
public class DefaultTextArea extends RSyntaxTextArea implements ITextArea {

    public DefaultTextArea() {
        this.setSize(20,60);
        this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        this.setCodeFoldingEnabled(true);
        this.setAutoscrolls(true);
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

    @Override
    public Document GetDocument() { return this.getDocument(); }

    @Override
    public Highlighter GetHighlighter() { return this.getHighlighter(); }

}
