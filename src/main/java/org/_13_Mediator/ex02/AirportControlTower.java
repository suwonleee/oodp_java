package org._13_Mediator.ex02;

class AirportControlTower implements AirportMediator {
    private boolean isRunwayAvailable = true;

    public boolean isRunwayAvailable() {
        return isRunwayAvailable;
    }

    public void setRunwayAvailability(boolean status) {
        isRunwayAvailable = status;
    }
}