package com.willingfish.tree.behavior.demo.tree;

import com.willingfish.tree.behavior.core.api.IShowBinNode;

public class RBNode<T> extends TreeNode<T> implements IShowBinNode<T> {

    public RBNode(T t){
        super(t);
        isRed=true;
    }
    boolean isRed ;

    public boolean isRed() {
        return isRed;
    }

    public void setRed(boolean red) {
        isRed = red;
    }

    @Override
    public RBNode<T> getRight() {
        return (RBNode)super.getRight();
    }

    @Override
    public RBNode<T> getLeft() {
        return (RBNode)super.getLeft();
    }

    @Override
    public RBNode<T> getParent() {
        return (RBNode)super.getParent();
    }

    @Override
    public String toString() {
        return this.getData().toString();
    }
}
