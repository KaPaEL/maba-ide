package Commands.ShellCommand;

import Commands.ICommand;
import FileExplorer.IFileExplorer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by mfrazi on 15/11/2016.
 */

public class ShellCommand implements ICommand{
    private IFileExplorer iFileExplorer;
    public String command = "";
    public String resultExe = "";
    private String outputExe = "";
    
    public ShellCommand(IFileExplorer iFileExplorer){
        this.iFileExplorer = iFileExplorer;
    }
    
    @Override
    public void execute(){
        this.UpdateCommand();
        Thread t = new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.print(command);
                String output = "";
//                String command = this.getClass().;
                Process p;
                try{
                    p = Runtime.getRuntime().exec(command);
                    p.waitFor();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line = "";
                    
                    while((line = reader.readLine())!=null){
                        output += line+"\n";
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                System.out.println("\n"+output);
                resultExe = output;
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
