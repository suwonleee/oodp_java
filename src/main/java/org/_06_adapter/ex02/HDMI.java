package org._06_adapter.ex02;

class HDMI {
    void connectWithHdmiCable(int resolution) {
        System.out.println(
                "Displaying via HDMI with resolution: " + resolution + "p"
        );
    }
}