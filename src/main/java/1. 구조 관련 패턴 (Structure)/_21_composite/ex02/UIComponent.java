package org._21_composite.ex02;

interface UIComponent {
    void render();
    void add(UIComponent component);
    void remove(UIComponent component);
}