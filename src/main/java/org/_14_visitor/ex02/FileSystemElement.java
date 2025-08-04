package org._14_visitor.ex02;

// FileSystemElement interface
interface FileSystemElement {
    void accept(Visitor visitor);
}