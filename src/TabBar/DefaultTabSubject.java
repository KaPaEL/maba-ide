package TabBar;


import Editor.DefaultTextArea;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

/**
 * Created by winasforcepta on 11/29/2016.
 */
public class DefaultTabSubject implements ITabSubject {
    private DefaultTabEditor activeTab;
    private List<ITabObserver> observers = null;
    private static DefaultTabSubject selfSubject = new DefaultTabSubject();
    private final int textAreaIdx = 0, mainFrameIdx = 1;

    public static DefaultTabSubject getInstance() {
        return selfSubject;
    }

    private DefaultTabSubject() {
        this.observers = new ArrayList<ITabObserver>();
        this.activeTab = null;
    }

    public DefaultTextArea getTextArea() {
        if (this.observers.size() >= 2) return (DefaultTextArea) this.observers.get(1);
        else return null;
    }


    public UUID getActiveTabId() {
        return this.activeTab.getTabId();
    }

    public DefaultTabEditor getActiveTab() {
        return this.activeTab;
    }

    public void setActiveTab(DefaultTabEditor _activeTab) {
        this.activeTab = _activeTab;
    }

    public void attachObserver(ITabObserver observer) {

        if (this.observers.size() > 1) {
            DefaultTabEditor temp = (DefaultTabEditor) observer;
            temp.pushCommandUndoStack("");
            this.observers.add(temp);
        } else {
            this.observers.add(observer);
        }

        if (this.observers.size() > 2 && this.activeTab == null) {
            this.setActiveTab((DefaultTabEditor) this.observers.get(this.observers.size() - 1));
        }
    }

    public void removeObserver(ITabObserver tabEditor) {
        for (int idx = 1; idx < this.observers.size(); idx++) {
            if (this.observers.get(idx).equals(tabEditor)) {
                this.observers.remove(idx);
                return;
            }
        }

        // Assume the front object in the list is always the text area
        if (this.observers.size() <= 2) {
            this.setActiveTab(null);
        } else {
            this.setActiveTab((DefaultTabEditor) this.observers.get(this.observers.size() - 1));
        }
    }


    public void selectTab(String tabName) {
        for (int idx = observers.size() - 1; idx > 1; idx--) {
            DefaultTabEditor temp = (DefaultTabEditor) this.observers.get(idx);
            if (temp.getTabName().toString().equals(tabName)) {
                this.setActiveTab(temp);
                this.update();
                return;
            }
        }
    }

    public void update() {

        System.out.println("[DEBUG] Undo Stack Size = " + this.activeTab.getCommandUndoStackSize());
        System.out.println("[DEBUG] Redo Stack Size = " + this.activeTab.getCommandRedoStackSize());
        for (ITabObserver obj : this.observers) {
            obj.update(this.activeTab);
        }
    }
}
