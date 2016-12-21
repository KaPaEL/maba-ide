package Commands.ShellCommand;

import Commands.Save;
import FileExplorer.IFileExplorer;

import javax.swing.*;

/**
 * Created by mfrazi on 15/11/2016.
 */
public class Compile extends ShellCommand{
    public Compile(JTextPane terminalText){
        super(terminalText);
    }
    
    @Override
    public boolean UpdateCommand(){
        super.UpdateCommand();
        Save save = new Save();
        save.execute();
        String path = this.GetPath();
        if(path=="")
            path=".";
        String fileName = this.GetFileName();
        String outputExe = path+"\\"+fileName.split("\\.c")[0];
        String command = "gcc \""+path+"\\"+fileName+"\" -o \""+outputExe+"\"";
        this.SetOutputExe(outputExe);
        this.SetCommand(command);
        this.SetCommandMessage("Compile");
        return true;
    }
}
