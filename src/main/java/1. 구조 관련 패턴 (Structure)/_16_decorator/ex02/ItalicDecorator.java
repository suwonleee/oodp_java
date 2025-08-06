package org._16_decorator.ex02;

class ItalicDecorator extends TextDecorator {
    public ItalicDecorator(Text text) {
        super(text);
    }

    @Override
    public String getContent() {
        return "<i>" + super.getContent() + "</i>";
    }
}