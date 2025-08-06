package org._12_factory_method.ex01;

class MacOSButton implements Button {
    @Override
    public void paint() {
        System.out.println("Rendering a button in MacOS style");
    }
}