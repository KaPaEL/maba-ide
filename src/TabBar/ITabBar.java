package TabBar;

/**
 * Created by winasforcepta on 11/30/2016.
 */
public interface ITabBar {
    void addTab(DefaultTabEditor tabEditor);
    void removeTab(DefaultTabEditor tabEditor);
    void selectTab(int idx);
}
