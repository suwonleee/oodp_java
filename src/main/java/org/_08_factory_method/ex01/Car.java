package org._08_factory_method.ex01;

class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a car");
    }
}