package Editor;

import TabBar.DefaultTabEditor;
import TabBar.DefaultTabSubject;
import TabBar.ITabObserver;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by hanu on 11/16/16.
 */
public class DefaultTextArea extends RSyntaxTextArea implements ITextArea, ITabObserver {

    private DefaultTabSubject subject;
    public DefaultTabEditor defaultTabEditor;

    static Timer timer;

    public DefaultTextArea() {
        this.subject = DefaultTabSubject.getInstance();
        this.setSize(20,60);
        this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        this.setCodeFoldingEnabled(true);
        this.setAutoscrolls(true);
        //commandRedoStack = null;
        //defaultTabEditor.pushCommandUndoStack(GetText());

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(defaultTabEditor.getCommandUndoStackSize() == 1 && defaultTabEditor.getflag() == 0)
                {
                    defaultTabEditor.pushCommandUndoStack(GetText());
                    defaultTabEditor.setFlag(1);
                }


                if ((keyEvent.getKeyCode() == KeyEvent.VK_V) && ((keyEvent.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                    if(!defaultTabEditor.peekCommandUndoStack().equals(GetText())  && defaultTabEditor.isCommandRedoStackEmpty() )
                    {
                        //System.out.println("masuk paste");
                        defaultTabEditor.pushCommandUndoStack(GetText());
                    }
                }

                else if ((keyEvent.getKeyCode() == KeyEvent.VK_X) && ((keyEvent.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                    if(!defaultTabEditor.peekCommandUndoStack().equals(GetText())  && defaultTabEditor.isCommandRedoStackEmpty() )
                    {
                        //System.out.println("masuk cut");
                        defaultTabEditor.pushCommandUndoStack(GetText());
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if(defaultTabEditor.peekCommandUndoStack().equals(GetText()))
                {
                    for (int i = defaultTabEditor.getCommandRedoStackSize();i>0;i--)
                    {
                        defaultTabEditor.popCommandRedoStack();
                    }
                }
                defaultTabEditor.setCounter(0);
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        defaultTabEditor.setCounter(defaultTabEditor.getCounter() + 1);
                    }
                };
                Thread t = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        while (true) {
                            try {
                                if(defaultTabEditor.getCounter() == 4)
                                {
                                    if(!defaultTabEditor.peekCommandUndoStack().equals(GetText()) && defaultTabEditor.isCommandRedoStackEmpty())
                                    {
                                        defaultTabEditor.pushCommandUndoStack(GetText());
                                        timer.cancel();
                                        break;
                                    }
                                }
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
                timer = new Timer("MyTimer");
                timer.scheduleAtFixedRate(timerTask, 0, 1000);
                t.start();
            }
        });
    }

    public void setDefaultTabEditor(DefaultTabEditor _defaultTabEditor) {
        this.defaultTabEditor = _defaultTabEditor;
    }

    public void pushUndoCommand(String command) {
        this.defaultTabEditor.pushCommandUndoStack(command);
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


    public void update(DefaultTabEditor tabEditor) {
        this.defaultTabEditor.setTextContent(GetText());
        this.defaultTabEditor = tabEditor;
        this.SetText(this.defaultTabEditor.getTextContent());
    }
}
