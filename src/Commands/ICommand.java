package Commands;


import java.io.FileNotFoundException;

/**
 * Created by hanu on 11/15/16.
 */
public interface ICommand {
    void execute() throws FileNotFoundException;
}
