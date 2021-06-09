package com.willingfish.tree.behavior.demo.tree;

import java.util.function.Consumer;

public interface IBinaryTree<T> {

    TreeNode<T> getRoot();

    TreeNode<T> insert(T t);

    void clear();

    TreeNode<T> delete(T t);

    void traversal(Consumer<TreeNode> visitor);


}
