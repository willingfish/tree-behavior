package com.willingfish.tree.behavior.core.api;


public interface IShowBinNode<T> {

    IShowBinNode getParent();

    IShowBinNode getLeft();

    IShowBinNode getRight();

    boolean isRed();

    T getData();
}
