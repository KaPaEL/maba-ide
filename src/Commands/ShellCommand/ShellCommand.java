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
    private String command = "";
    private String outputExe = "";
    
    public ShellCommand(IFileExplorer iFileExplorer){
        this.iFileExplorer = iFileExplorer;
    }
    
    @Override
    public void execute(){
        this.UpdateCommand();
//        System.out.println(this.iFileExplorer.GetPath()+"\\"+this.iFileExplorer.GetFileName());
        System.out.print(this.command);
        String output = "";
        String command = this.command;
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
