package com.willingfish.tree.behavior.core.ui.datapanel;


import com.willingfish.tree.behavior.core.api.IShowBinNode;
import com.willingfish.tree.behavior.core.ui.SwingConsts;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

class InternalTree {
    Map<IShowBinNode, InternalNode> maps = Maps.newConcurrentMap();
    InternalNode root;

    public void preOrderTraversal(Consumer<InternalNode> consumer) {
        doPreOrderTraversal(root, consumer);
    }

    private void doPreOrderTraversal(InternalNode node, Consumer consumer) {
        if (Objects.isNull(node)) {
            return;
        }
        consumer.accept(node);
        doPreOrderTraversal(node.getLeft(), consumer);
        doPreOrderTraversal(node.getRight(), consumer);
    }

    public void clear() {
        root = null;
        maps.clear();
    }

    public InternalNode addNode(IShowBinNode binNode) {
        InternalNode internalNode = new InternalNode();
        internalNode.setData(binNode);
        maps.put(binNode, internalNode);

        if (root == null) {
            root = internalNode;
            internalNode.setDepth(0);
        } else {
            IShowBinNode parent = binNode.getParent();
            InternalNode parentInternalNode = maps.get(parent);
            internalNode.setParent(parentInternalNode);
            internalNode.setDepth(parentInternalNode.getDepth() + 1);
            boolean isLeft = parent.getLeft() == binNode;
            if (isLeft) {
                parentInternalNode.setLeft(internalNode);
            } else {
                parentInternalNode.setRight(internalNode);
            }
        }
        populatePoint(internalNode);
        return internalNode;
    }

    private void populatePoint(InternalNode internalNode) {
        int x, y;
        if (Objects.isNull(internalNode.getParent())) {
            x = SwingConsts.SHOW_PANEL_WIDTH >> 1;
            y = 10;
        } else {
            int xOffset = SwingConsts.SHOW_PANEL_WIDTH >> internalNode.getDepth() + 1;
            if (internalNode == internalNode.getParent().getLeft()) {
                x = internalNode.getParent().getX() - xOffset;
            } else {
                x = internalNode.getParent().getX() + xOffset;
            }
            y = internalNode.getParent().getY() + 70;
        }
        internalNode.setX(x);
        internalNode.setY(y);
    }
}
