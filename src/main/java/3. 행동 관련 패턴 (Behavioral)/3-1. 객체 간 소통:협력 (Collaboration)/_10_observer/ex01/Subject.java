package org._10_observer.ex01;

// Subject (Publisher) interface
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}