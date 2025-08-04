package org._07_bridge.ex01;

// Implementor
interface Device {
    void turnOn();
    void turnOff();
    void setVolume(int volume);
    boolean isEnabled();
}