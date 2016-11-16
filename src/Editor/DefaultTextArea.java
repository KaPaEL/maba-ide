package Editor;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

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

}
