package Commands.ShellCommand;

import FileExplorer.IFileExplorer;

import javax.swing.*;

/**
 * Created by mfrazi on 15/11/2016.
 */
public class Run extends ShellCommand{
    public Run(JTextPane terminalText){
        super(terminalText);
    }
    
    @Override
    public void UpdateCommand(){
        super.UpdateCommand();
        String path = this.GetPath();
        if(path=="")
            path=".";
        String fileName = this.GetFileName();
        String outputExe = "cmd /c start cmd /c (\"C:\\Program Files (x86)\\Dev-Cpp\\ConsolePauser.exe\" \""+path+"\\"+fileName.split("\\.c")[0]+"\")";
        this.SetOutputExe(outputExe);
        this.SetCommand(outputExe);
    }
}
