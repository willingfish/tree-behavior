package com.example.tree.behavior.core.api;


public interface IShowBinNode<T> {

    IShowBinNode getParent();

    IShowBinNode getLeft();

    IShowBinNode getRight();

    boolean isRed();

    T getData();
}
