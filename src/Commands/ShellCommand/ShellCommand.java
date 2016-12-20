package Commands.ShellCommand;

import Commands.ICommand;
import FileExplorer.IFileExplorer;
import TabBar.DefaultTabEditor;
import TabBar.DefaultTabSubject;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by mfrazi on 15/11/2016.
 */

public class ShellCommand implements ICommand{
    private DefaultTabSubject subject;
    private DefaultTabEditor defaultTabEditor;
    public JTextPane terminalText;
    public String command = "";
    public String commandMessage = "";
    //    public String resultExe = "";
    private String outputExe = "";
    
    public ShellCommand(JTextPane terminalText){
        this.subject = DefaultTabSubject.getInstance();
        this.defaultTabEditor = this.subject.getActiveTab();
        this.terminalText = terminalText;
    }
    
    @Override
    public void execute(){
        this.defaultTabEditor = this.subject.getActiveTab();
        if(this.UpdateCommand()){
            this.terminalText.setText("\n");
    
            Style primary = terminalText.addStyle("style", null);
            StyleConstants.setForeground(primary, Color.black);
            try{
                StyledDocument defaultTerminal = terminalText.getStyledDocument();
                defaultTerminal.insertString(defaultTerminal.getLength(), commandMessage+"...\nPlease Wait...\n", primary);
            }catch(BadLocationException exc){
                exc.printStackTrace();
            }
            terminalText.repaint();
            terminalText.invalidate();
    
            Thread t = new Thread(new Runnable(){
                @Override
                public void run(){
                    System.out.print(command);
                    String output = "";
                    Process p;
                    try{
                        p = Runtime.getRuntime().exec(command);
                        p.waitFor();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                        String line = "";
                        while((line = reader.readLine())!=null){
                            output += line+"\n";
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    System.out.println("\n"+output);
                    //                resultExe = output;
                    try{
                        StyledDocument doc = terminalText.getStyledDocument();
                        if(output.length()==0){
                            Style success = terminalText.addStyle("style", null);
                            StyleConstants.setForeground(success, Color.green);
                            doc.insertString(doc.getLength(), "\nProcess is completed\n", success);
                        }
                        else{
                            Style error = terminalText.addStyle("style", null);
                            StyleConstants.setForeground(error, Color.red);
                            output = "\nError :\n"+output;
                            doc.insertString(doc.getLength(), output, error);
                        }
                        terminalText.repaint();
                        terminalText.invalidate();
                    }catch(BadLocationException exc){
                        exc.printStackTrace();
                    }
                    //                terminalText.setText(output);
                }
            });
            t.start();
        }
        else{
            Style error = terminalText.addStyle("style", null);
            StyleConstants.setForeground(error, Color.red);
            try{
                StyledDocument defaultTerminal = terminalText.getStyledDocument();
                defaultTerminal.insertString(defaultTerminal.getLength(), "\nExecutable file not found\nTry compile first", error);
            }catch(BadLocationException exc){
                exc.printStackTrace();
            }
            terminalText.repaint();
            terminalText.invalidate();
        }
    }
    
    public void SetCommand(String command){
        this.command = command;
    }
    
    public void SetCommandMessage(String commandMessage){
        this.commandMessage = commandMessage;
    }
    
    public void SetOutputExe(String outputExe){
        this.outputExe = outputExe;
    }
    
    public String GetOutputExe(){
        return this.outputExe;
    }
    
    public String GetPath(){
        return this.defaultTabEditor.getFilePath();
    }
    
    public String GetFileName(){
        return this.defaultTabEditor.getTabName();
    }
    
    public boolean UpdateCommand(){
        // Do nothing
        return true;
    }
}
