package Commands.ShellCommand;

import FileExplorer.IFileExplorer;

import javax.swing.*;

/**
 * Created by mfrazi on 15/11/2016.
 */
public class Run extends ShellCommand{
    public Run(IFileExplorer iFileExplorer, JTextPane terminalText){
        super(iFileExplorer, terminalText);
    }
    
    @Override
    public void UpdateCommand(){
        super.UpdateCommand();
        String path = this.GetPath();
        String fileName = this.GetFileName();
        String outputExe = "cmd /c start cmd /c (\"D:\\GitRepo\\maba-ide\\library\\ConsolePauser.exe\" \""+path+"\\"+fileName.split("\\.c")[0]+"\")";
        this.SetOutputExe(outputExe);
        this.SetCommand(outputExe);
    }
}
