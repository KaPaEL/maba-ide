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
        //defaultTabEditor.pushCommandUndoStack(GetText());

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(defaultTabEditor.getflagThread()==0)
                {
                    defaultTabEditor.setFlagThread(1);
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
                                    if(defaultTabEditor.getCounter() == 2)
                                    {
                                        // System.out.println("[DEBUG] counter is 4");
                                        if(!defaultTabEditor.isCommandUndoStackEmpty() && !defaultTabEditor.peekCommandUndoStack().equals(GetText()) && defaultTabEditor.isCommandRedoStackEmpty())
                                        {
                                            System.out.println("[DEBUG] pushed to undo stack");
                                            defaultTabEditor.pushCommandUndoStack(GetText());
                                        }
                                        defaultTabEditor.setCounter(0);
                                    }
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    });
                    t.interrupt();
                    timer = new Timer("MyTimer");
                    timer.scheduleAtFixedRate(timerTask, 0, 1000);
                    t.setDaemon(true);
                    t.start();
                    System.out.println(t.getState());
                }

                if(defaultTabEditor != null && defaultTabEditor.getCommandUndoStackSize() == 1 && defaultTabEditor.getflag() == 0)
                {
                    defaultTabEditor.pushCommandUndoStack(GetText());
                    defaultTabEditor.setFlag(1);
                }


                if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE ) {
                    if(!defaultTabEditor.isCommandUndoStackEmpty() && !defaultTabEditor.peekCommandUndoStack().equals(GetText().trim())  && defaultTabEditor.isCommandRedoStackEmpty() )
                    {
                        defaultTabEditor.setCounter(0);
                        System.out.println("[DEBUG] pushed to undo stack space");
                        defaultTabEditor.pushCommandUndoStack(GetText());
                    }
                }

                else if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(!defaultTabEditor.isCommandUndoStackEmpty() && !defaultTabEditor.peekCommandUndoStack().equals(GetText().trim().replace("\n", "").replace("\r", ""))  && defaultTabEditor.isCommandRedoStackEmpty() )
                    {
                        defaultTabEditor.setCounter(0);
                        System.out.println("[DEBUG] pushed to undo stack enter");
                        defaultTabEditor.pushCommandUndoStack(GetText());
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if(!defaultTabEditor.isCommandUndoStackEmpty() && defaultTabEditor.peekCommandUndoStack().equals(GetText()))
                {
                    for (int i = defaultTabEditor.getCommandRedoStackSize();i>0;i--)
                    {
                        defaultTabEditor.popCommandRedoStack();
                    }
                }

            }
        });
    }

    public void setDefaultTabEditor(DefaultTabEditor _defaultTabEditor) {
        this.defaultTabEditor = _defaultTabEditor;
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
        System.out.println("[DEBUG Text Area] update with text: " + tabEditor.getTextContent());
        this.defaultTabEditor.setTextContent(GetText());
        this.defaultTabEditor = tabEditor;
        this.SetText(tabEditor.getTextContent());
    }
}
