package com.example.tree.behavior.demo.tree;


import com.example.tree.behavior.core.api.IShowBinNode;
import com.example.tree.behavior.core.api.IShowTree;

import java.util.Objects;
import java.util.function.Consumer;

public abstract class BinaryTree<T> implements IShowTree<T> {
    TreeNode<T> root;

    @Override
    public IShowBinNode<T> getRoot() {
        return root;
    }

    @Override
    public void traversal(Consumer<IShowBinNode> consumer) {
        doPreOrderTraversal(consumer,root);
    }

    protected TreeNode createNode(T t){
        TreeNode node = new TreeNode(t);
        return node;
    }

    private void doPreOrderTraversal(Consumer<IShowBinNode> consumer,IShowBinNode treeNode){
        if (Objects.isNull(treeNode)){
            return;
        }
        consumer.accept(treeNode);
        doPreOrderTraversal(consumer,treeNode.getLeft());
        doPreOrderTraversal(consumer,treeNode.getRight());
    }
}
