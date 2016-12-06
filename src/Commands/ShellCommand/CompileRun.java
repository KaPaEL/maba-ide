package Commands.ShellCommand;

import FileExplorer.IFileExplorer;

import javax.swing.*;

/**
 * Created by mfrazi on 15/11/2016.
 */
public class CompileRun extends ShellCommand{
    public CompileRun(IFileExplorer iFileExplorer, JTextPane terminalText){
        super(iFileExplorer, terminalText);
    }
}
