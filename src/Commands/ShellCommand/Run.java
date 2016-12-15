package Commands.ShellCommand;

import FileExplorer.IFileExplorer;
import Main.MainWindow;

import javax.swing.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

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
        URL url = MainWindow.class.getProtectionDomain().getCodeSource().getLocation(); //Gets the path
        String jarPath = null;
        try {
            jarPath = URLDecoder.decode(url.getFile(), "UTF-8"); //Should fix it to be read correctly by the system
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String parentPath = new File(jarPath).getParentFile().getPath(); //Path of the jar


        String path = this.GetPath();
        if(path=="")
            path=".";
        String fileName = this.GetFileName();
        String outputExe = "cmd /c start cmd /c (\""+parentPath+"\\ConsolePauser.exe\" \""+path+"\\"+fileName.split("\\.c")[0]+"\")";
//        String outputExe = "cmd /c start cmd /c (\"C:\\Program Files (x86)\\Dev-Cpp\\ConsolePauser.exe\" \""+path+"\\"+fileName.split("\\.c")[0]+"\")";
        this.SetOutputExe(outputExe);
        this.SetCommand(outputExe);
    }
}
