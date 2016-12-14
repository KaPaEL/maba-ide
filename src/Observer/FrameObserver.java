package Observer;

import TabBar.DefaultTabEditor;
import TabBar.ITabObserver;

import javax.swing.*;

/**
 * Created by winasforcepta on 12/14/2016.
 */
public class FrameObserver extends JFrame implements ITabObserver {

    public FrameObserver(String title) {
        super(title);
    }

    public void update(DefaultTabEditor editor) {
        super.setTitle("MABA IDE" + editor.getFilePath() + editor.getTabName());
    }
}
