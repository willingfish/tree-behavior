package com.example.tree.behavior.core.api;

import java.util.function.Consumer;

public interface IShowTree<T> {

    IShowBinNode insert(T value);

    IShowBinNode delete(T value);

    void clear();

    IShowBinNode<T> getRoot();

    void traversal(Consumer<IShowBinNode> consumer);
}
