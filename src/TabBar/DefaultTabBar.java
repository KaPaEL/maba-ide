package TabBar;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by winasforcepta on 11/30/2016.
 */
public class DefaultTabBar implements ITabBar{

    private static DefaultTabBar instance = new DefaultTabBar();
    private JTabbedPane tabbedPane;

    private DefaultTabBar() {
        this.tabbedPane = new JTabbedPane();
        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
                if (sourceTabbedPane.getTabCount()>0) {
                    int index = sourceTabbedPane.getSelectedIndex();
                    selectTab(index);
                }
            }
        };
        tabbedPane.addChangeListener(changeListener);
    }

    public JTabbedPane getTabbedPane() {
        return this.tabbedPane;
    }

    public static DefaultTabBar getInstance() {
        return instance;
    }

    public void addTab(DefaultTabEditor tabEditor) {
        tabbedPane.addTab(tabEditor.getTabName().toString(), makePanel(""));
        this.selectTab(tabbedPane.getTabCount() - 1);
    }

    public void removeTab(DefaultTabEditor tabEditor) {
        System.out.println("[DEBUG] tabpane size: " + tabbedPane.getTabCount());
        System.out.println("[DEBUG] index of deleted tab: " + tabbedPane.indexOfTab(tabEditor.getTabName().toString()));
        int idx = tabbedPane.indexOfTab(tabEditor.getTabName().toString());
        tabbedPane.remove(idx);
        System.out.println("[DEBUG] remaining tabpane size: " + tabbedPane.getTabCount());

    }

    public void selectTab(int idx) {
        if (idx < tabbedPane.getTabCount()) {
            tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
            DefaultTabSubject.getInstance().selectTab(tabbedPane.getTitleAt(idx));
        }
    }

    private JPanel makePanel(String text) {
        JPanel p = new JPanel();
        p.add(new Label(text));
        p.setLayout(new GridLayout(1, 1));

        p.setVisible(false);
        return p;
    }

}