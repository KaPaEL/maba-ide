package Editor;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by hanu on 11/16/16.
 */
public class DefaultTextArea extends RSyntaxTextArea implements ITextArea {

    public Stack commandUndoStack = new Stack();
    public Stack commandRedoStack = new Stack();

    static int counter = 0;
    static Timer timer;
    public DefaultTextArea() {
        this.setSize(20,60);
        this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        this.setCodeFoldingEnabled(true);
        this.setAutoscrolls(true);
        //commandRedoStack = null;
        commandUndoStack.push(GetText());

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                //System.out.println("masuk ptype");
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                //commandRedoStack = null;
                if(!commandRedoStack.empty() && !commandUndoStack.peek().equals(GetText()))
                {
                    //System.out.println("peek"+GetStackRedoText().peek().toString().trim()+" - "+GetText().trim());
                    for (int i = GetStackRedoText().size();i>0;i--)
                    {
                        commandRedoStack.pop();
                    }
                    //System.out.println("akhir"+GetStackRedoText().size());
                }

                if ((keyEvent.getKeyCode() == KeyEvent.VK_V) && ((keyEvent.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                    if(!commandUndoStack.peek().equals(GetText())  && commandRedoStack.isEmpty() )
                    {
                        //System.out.println("masuk paste");
                        commandUndoStack.push(GetText());
                    }
                }

                else if ((keyEvent.getKeyCode() == KeyEvent.VK_X) && ((keyEvent.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
                    if(!commandUndoStack.peek().equals(GetText())  && commandRedoStack.isEmpty() )
                    {
                        //System.out.println("masuk cut");
                        commandUndoStack.push(GetText());
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                counter=0;
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        counter++;
                    }
                };
                Thread t = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        while (true) {
                            try {
                                if(counter==4)
                                {
                                    if(!commandUndoStack.peek().equals(GetText()) && commandRedoStack.isEmpty())
                                    {
                                        commandUndoStack.push(GetText());
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

    @Override
    public Stack GetStackUndoText() { return commandUndoStack; }

    @Override
    public Stack GetStackRedoText() { return commandRedoStack; }

}
