package org._10_observer.ex02;

// Observer interface
interface WeatherObserver {
    void update(float temp, float humidity, float pressure);
}