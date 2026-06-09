package com.nikita.arraysapp.observer;

import java.util.ArrayList;
import java.util.List;

public class Observer {
    private final List<Runnable> actions = new ArrayList<>();

    public void subscribe(Runnable action) {
        if (action != null) {
            actions.add(action);
        }
    }

    public void fireEvent() {
        for (Runnable action : actions) {
            action.run();
        }
    }
}