package com.bartczakdawid.core.navigation;

import java.util.HashMap;

public class ViewManager {
    private static ViewManager viewManagerInstance;
    private final HashMap<ViewType, ManageableView> manageableViews;

    private ViewManager() {
        this.manageableViews = new HashMap<>();
    }

    public static ViewManager getInstance() {
        if (viewManagerInstance == null) {
            viewManagerInstance = new ViewManager();
        }

        return viewManagerInstance;
    }

    public void toggleView(ViewType viewType, boolean toggle) {
        ManageableView manageableView = manageableViews.get(viewType);

        if (toggle) {
            manageableView.showView();
        } else {
            manageableView.hideView();
        }
    }

    public void hideAllViews() {
        this.manageableViews.forEach((_, manageableView) -> manageableView.hideView());
    }

    public void addView(ViewType viewType, ManageableView manageableView) {
        this.manageableViews.put(viewType, manageableView);
    }
}
