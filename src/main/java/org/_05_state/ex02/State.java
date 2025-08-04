package org._05_state.ex02;

public interface State {
    void play(VideoPlayer player);
    void stop(VideoPlayer player);
}