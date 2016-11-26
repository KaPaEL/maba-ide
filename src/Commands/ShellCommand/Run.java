package Commands.ShellCommand;

import FileExplorer.IFileExplorer;

/**
 * Created by mfrazi on 15/11/2016.
 */
public class Run extends ShellCommand{
    public Run(IFileExplorer iFileExplorer){
        super(iFileExplorer);
    }
    
    @Override
    public void UpdateCommand(){
        super.UpdateCommand();
        String path = this.GetPath();
        String fileName = this.GetFileName();
        String outputExe = "\""+path+"\\"+fileName.split("\\.c")[0]+"\"";
        this.SetOutputExe(outputExe);
        this.SetCommand(outputExe);
    }
}
