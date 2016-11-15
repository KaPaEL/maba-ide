package Commands;

/**
 * Created by hanu on 11/15/16.
 */
public class CExit implements ICommand{
    public CExit() {
    }

    @Override

    public void Execute() {
        System.out.println("Exit Now");
        System.exit(0);
    }
}
