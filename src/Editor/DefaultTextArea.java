package Editor;

import TabBar.DefaultTabEditor;
import TabBar.DefaultTabSubject;
import TabBar.ITabObserver;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by hanu on 11/16/16.
 */
public class DefaultTextArea extends RSyntaxTextArea implements ITextArea, ITabObserver {

    private DefaultTabSubject subject;
    public DefaultTabEditor defaultTabEditor;
    private SuggestionPanel suggestion;
    private JTextArea textarea;
    static Timer timer;
    public ArrayList<String> template = new ArrayList<String>();

    public DefaultTextArea() {
        this.subject = DefaultTabSubject.getInstance();
        this.setSize(20, 60);
        this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        this.setCodeFoldingEnabled(true);
        this.setAutoscrolls(true);
        textarea = this;
        //defaultTabEditor.pushCommandUndoStack(GetText());

        textarea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    //System.out.println("masuk");
                    if (suggestion != null) {
                        if (suggestion.insertSelection()) {
                            e.consume();
                            final int position = textarea.getCaretPosition();
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        textarea.getDocument().remove(position - 1, 1);
                                    } catch (BadLocationException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (defaultTabEditor.getflagThread() == 0) {
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
                                    if (defaultTabEditor.getCounter() == 2) {
                                        // System.out.println("[DEBUG] counter is 4");
                                        if (!defaultTabEditor.isCommandUndoStackEmpty() && !defaultTabEditor.peekCommandUndoStack().equals(GetText()) && defaultTabEditor.isCommandRedoStackEmpty()) {
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

                if (defaultTabEditor != null && defaultTabEditor.getCommandUndoStackSize() == 1 && defaultTabEditor.getflag() == 0) {
                    defaultTabEditor.pushCommandUndoStack(GetText());
                    defaultTabEditor.setFlag(1);
                }


                if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (!defaultTabEditor.isCommandUndoStackEmpty() && !defaultTabEditor.peekCommandUndoStack().equals(GetText().trim()) && defaultTabEditor.isCommandRedoStackEmpty()) {
                        defaultTabEditor.setCounter(0);
                        System.out.println("[DEBUG] pushed to undo stack space");
                        defaultTabEditor.pushCommandUndoStack(GetText());
                    }
                } else if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!defaultTabEditor.isCommandUndoStackEmpty() && !defaultTabEditor.peekCommandUndoStack().equals(GetText().trim().replace("\n", "").replace("\r", "")) && defaultTabEditor.isCommandRedoStackEmpty()) {
                        defaultTabEditor.setCounter(0);
                        System.out.println("[DEBUG] pushed to undo stack enter");
                        defaultTabEditor.pushCommandUndoStack(GetText());
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN && suggestion != null) {
                    suggestion.moveDown();
                } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP && suggestion != null) {
                    suggestion.moveUp();
                } else if (Character.isLetterOrDigit(keyEvent.getKeyChar())) {
                    showSuggestionLater();
                } else if (Character.isWhitespace(keyEvent.getKeyChar())) {
                    hideSuggestion();
                }

                if (!defaultTabEditor.isCommandUndoStackEmpty() && defaultTabEditor.peekCommandUndoStack().equals(GetText())) {
                    for (int i = defaultTabEditor.getCommandRedoStackSize(); i > 0; i--) {
                        defaultTabEditor.popCommandRedoStack();
                    }
                }

            }
        });

    }

    public class SuggestionPanel {
        private JList list;
        private JPopupMenu popupMenu;
        private String subWord;
        private final int insertionPosition;
        //private JTextArea textarea;


        public SuggestionPanel(JTextArea textarea, int position, String subWord, Point location) {
            //this.textarea = textarea;
            this.insertionPosition = position;
            this.subWord = subWord;
            popupMenu = new JPopupMenu();
            popupMenu.removeAll();
            popupMenu.setOpaque(false);
            popupMenu.setBorder(null);
            popupMenu.add(list = createSuggestionList(position, subWord), BorderLayout.CENTER);
            popupMenu.show(textarea, location.x, textarea.getBaseline(0, 0) + location.y);

        }

        public void hide() {
            popupMenu.setVisible(false);
            if (suggestion == this) {
                suggestion = null;
            }
        }

        private JList createSuggestionList(final int position, final String subWord) {
            ArrayList<String> data = new ArrayList<String>();
            String temp = textarea.getText();
            template.clear();
            String[] splitted = temp.split("\\W+");
            template.add("char ");
            template.add("int ");
            template.add("float ");
            template.add("printf ");
            template.add("scanf ");
            template.add("string ");
            //int jumlah = template.size();
            for(int i = 0;i<splitted.length;i++)
            {
                template.add(splitted[i]+" ");
            }
            System.out.println("ukuran suggestion = "+template.size()+"");
            for (int a = 0;a<template.size();a++)
            {
                System.out.println(template.toArray()[a].toString());
                if(subWord.length() > template.toArray()[a].toString().length())
                {
                    continue;
                }
                else
                {
                    if(template.toArray()[a].toString().toLowerCase().substring(0,subWord.length()).equals(subWord.toLowerCase()))
                    {
                        int flag = 0;
                        for (int b = 0;b<data.size();b++)
                        {
                            if(template.toArray()[a].toString().equals(data.toArray()[b]))
                            {
                                flag = 1;
                                break;
                            }
                        }
                        if(flag == 0)
                        {
                            data.add(template.toArray()[a].toString());
                        }
                    }
                }
            }
            JList list = new JList(data.toArray());
            //list.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setSelectedIndex(0);
            list.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        insertSelection();
                        hide();
                    }
                }
            });
            return list;
        }

        public boolean insertSelection() {
            if (list.getSelectedValue() != null) {
                try {
                    final String selectedSuggestion = ((String) list.getSelectedValue()).substring(subWord.length());
                    textarea.getDocument().insertString(insertionPosition, selectedSuggestion, null);
                    return true;
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
                hideSuggestion();
            }
            return false;
        }

        public void moveUp() {
            int index = Math.min(list.getSelectedIndex() - 1, 0);
            selectIndex(index);
        }

        public void moveDown() {
            int index = Math.min(list.getSelectedIndex() + 1, list.getModel().getSize() - 1);
            selectIndex(index);
        }

        private void selectIndex(int index) {
            final int position = textarea.getCaretPosition();
            list.setSelectedIndex(index);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    textarea.setCaretPosition(position);
                };
            });
        }
    }


    protected void showSuggestionLater() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                showSuggestion();
            }

        });
    }

    protected void showSuggestion() {
        hideSuggestion();
        final int position = textarea.getCaretPosition();
        Point location;
        try {
            location = textarea.modelToView(position).getLocation();
        } catch (BadLocationException e2) {
            e2.printStackTrace();
            return;
        }
        String text = textarea.getText();
        int start = Math.max(0, position - 1);
        while (start > 0) {
            if (!Character.isWhitespace(text.charAt(start))) {
                start--;
            } else {
                start++;
                break;
            }
        }
        if (start > position) {
            return;
        }
        final String subWord = text.substring(start, position);
        if (subWord.length() < 1) {
            return;
        }
        suggestion = new SuggestionPanel(textarea, position, subWord, location);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                textarea.requestFocus(true);
            }
        });
    }

    private void hideSuggestion() {
        if (suggestion != null) {
            suggestion.hide();
        }
    }


    public void setDefaultTabEditor(DefaultTabEditor _defaultTabEditor) {
        this.defaultTabEditor = _defaultTabEditor;
    }


    @Override
    public void SetText(String text) {
        this.setText(text);
    }

    @Override
    public String GetText() {
        return this.getText();
    }

    @Override
    public String GetSelectedText() {
        return this.getSelectedText();
    }

    @Override
    public int GetSelectionStart() {
        return this.getSelectionStart();
    }

    @Override
    public int GetSelectionEnd() {
        return this.getSelectionEnd();
    }

    @Override
    public void ReplaceRange(String replace, int start, int end) {
        this.replaceRange(replace, start, end);
    }

    @Override
    public void SelectAll() {
        this.selectAll();
    }

    @Override
    public Document GetDocument() {
        return this.getDocument();
    }

    @Override
    public Highlighter GetHighlighter() {
        return this.getHighlighter();
    }


    public void update(DefaultTabEditor tabEditor) {
        if (tabEditor != null) {
            System.out.println("[DEBUG Text Area] update with text: " + tabEditor.getTextContent());
            if (this.defaultTabEditor != null) {
                this.defaultTabEditor.setTextContent(GetText());
            }
            this.defaultTabEditor = tabEditor;
            this.SetText(tabEditor.getTextContent());
        }

        if (this.subject.getObserverSize() > 2) {
            this.setVisible(true);
        } else {
            this.setVisible(false);
        }

    }
}