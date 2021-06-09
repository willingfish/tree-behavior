package com.example.tree.behavior.core.ui.datapanel;

import com.example.tree.behavior.core.api.IShowTree;

public class TreeFactory {
    InternalTree generate(IShowTree<Integer> tree) {
        InternalTree internalTree = new InternalTree();
        tree.traversal(node -> internalTree.addNode(node));
        return internalTree;
    }


}
