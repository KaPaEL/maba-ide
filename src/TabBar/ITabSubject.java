package TabBar;

import java.util.List;
import java.util.UUID;

public interface ITabSubject {

    DefaultTabEditor getActiveTab();

    void setActiveTab(DefaultTabEditor _activeTab);

    void attachObserver(ITabObserver observer);

    void removeObserver(ITabObserver tabId);
}
