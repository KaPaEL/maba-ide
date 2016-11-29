package Editor;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.util.Stack;

/**
 * Created by hanu on 11/16/16.
 */
public interface ITextArea {
    String text = null;
    void SetText(String text);
    String GetText();
    String GetSelectedText();
    int GetSelectionStart();
    int GetSelectionEnd();
    void ReplaceRange(String replace, int start, int end);
    void SelectAll();
    Document GetDocument();
    Highlighter GetHighlighter();

}
