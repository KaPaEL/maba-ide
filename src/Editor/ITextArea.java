package Editor;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

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
    void ReplaceRange(int start, int end);
}
