package Commands.ShellCommand;

import FileExplorer.IFileExplorer;

/**
 * Created by mfrazi on 15/11/2016.
 */
public class Compile extends ShellCommand{
    public Compile(IFileExplorer iFileExplorer){
        super(iFileExplorer);
    }
    
    @Override
    public void UpdateCommand(){
        super.UpdateCommand();
        String path = this.GetPath();
        String fileName = this.GetFileName();
        String outputExe = path+"\\"+fileName.split("\\.c")[0];
        String command = "gcc \""+path+"\\"+fileName+"\" -o \""+outputExe+"\"";
        this.SetOutputExe(outputExe);
        this.SetCommand(command);
    }
}
