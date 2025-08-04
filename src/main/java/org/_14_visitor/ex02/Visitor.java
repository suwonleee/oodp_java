package org._14_visitor.ex02;

interface Visitor {
    void visit(File file);
    void visit(Directory directory);
}