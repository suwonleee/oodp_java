package org._08_factory_method.ex01;

class CarFactory extends VehicleFactory {
    @Override
    Vehicle createVehicle() {
        return new Car();
    }
}