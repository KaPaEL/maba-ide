package Editor;

import MenuBar.DefaultMenuItem;
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
    int flag = 0;
    int flagThread = 0;
    static Timer timer;
    public DefaultTextArea() {
        this.setSize(20,60);
        this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        this.setCodeFoldingEnabled(true);
        this.setAutoscrolls(true);
        commandUndoStack.push(GetText());


        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(flagThread==0)
                {
                    System.out.println("flag masuk");
                    flagThread = 1;
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
                                    if(counter==2)
                                    {
                                        System.out.println("masuk thread");
                                        if(!commandUndoStack.peek().equals(GetText()) && commandRedoStack.isEmpty())
                                        {
                                            commandUndoStack.push(GetText());
                                        }
                                        counter = 0;
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
                if(commandUndoStack.size()==1 && flag == 0)
                {
                    commandUndoStack.push(GetText());
                    flag = 1;
                }

                if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE) {
                    if(!commandUndoStack.peek().equals(GetText().trim())  && commandRedoStack.isEmpty() )
                    {
                        commandUndoStack.push(GetText());
                    }
                }
                else if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(!commandUndoStack.peek().equals(GetText().trim().replace("\n", "").replace("\r", ""))  && commandRedoStack.isEmpty() )
                    {
                        commandUndoStack.push(GetText());
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if(!commandUndoStack.peek().equals(GetText()))
                {
                    for (int i = GetStackRedoText().size();i>0;i--)
                    {
                        commandRedoStack.pop();
                    }
                }

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
