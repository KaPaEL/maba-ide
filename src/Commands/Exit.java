package Commands;

/**
 * Created by hanu on 11/15/16.
 */
public class Exit implements ICommand{
    public Exit() {
    }

    @Override
    public void execute() {
        System.out.println("Exit Now");
        System.exit(0);
    }
}
