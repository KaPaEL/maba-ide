package Commands.ShellCommand;

import Commands.ICommand;
import FileExplorer.IFileExplorer;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by mfrazi on 15/11/2016.
 */

public class ShellCommand implements ICommand{
    private IFileExplorer iFileExplorer;
    public JTextPane terminalText;
    public String command = "";
//    public String resultExe = "";
    private String outputExe = "";
    
    public ShellCommand(IFileExplorer iFileExplorer, JTextPane terminalText){
        this.iFileExplorer = iFileExplorer;
        this.terminalText = terminalText;
    }
    
    @Override
    public void execute(){
        this.terminalText.setText("Compileeee....\n");
        this.UpdateCommand();
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
                        System.out.println("masuk");
                        output += line+"\n";
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                System.out.println("\n"+output);
//                resultExe = output;
                try {
                    Document doc = terminalText.getDocument();
                    if(output.length()==0){
                        doc.insertString(doc.getLength(), "Done", null);
                    }
                    else{
                        doc.insertString(doc.getLength(), output, null);
                    }
                } catch(BadLocationException exc) {
                    exc.printStackTrace();
                }
//                terminalText.setText(output);
            }
        });
        t.start();
    }
    
    public void SetCommand(String command){
        this.command = command;
    }
    
    public void SetOutputExe(String outputExe){
        this.outputExe = outputExe;
    }
    
    public String GetOutputExe(){
        return this.outputExe;
    }
    
    public String GetPath(){
        return this.iFileExplorer.GetPath();
    }
    
    public String GetFileName(){
        return this.iFileExplorer.GetFileName();
    }
    
    public void UpdateCommand(){
        // Do nothing
    }
}
