package ui;

import animation.Animation;

public interface Menu<T> extends Animation {
    void addSelection(String Key, String message, T returnVal);
    T getStatus();
}
