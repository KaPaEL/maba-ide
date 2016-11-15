package Commands.ShellCommand;

import Commands.ICommand;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by mfrazi on 15/11/2016.
 */

public class ShellCommand implements ICommand{
    private String command = "";
    
    @Override
    public void execute(){
        String output = "";
        // TODO: 15/11/2016 Ganti jadi sesuai dengan compilernya, pasing nama file dan output file
        String command = "ping google.com";
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
        
        System.out.println(output);
    }
    
    public String getCommand(){
        return command;
    }
    
    public void setCommand(String command){
        this.command = command;
    }
}
