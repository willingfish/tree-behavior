package com.willingfish.tree.behavior.demo.tree;

import com.willingfish.tree.behavior.core.api.IShowBinNode;
import com.google.common.base.Objects;
import lombok.Data;

@Data
public class TreeNode<T> implements IShowBinNode<T> {

    T data;
    boolean isRed;

    int height = 0;

    TreeNode<T> left;
    TreeNode<T> right;
    TreeNode<T> parent;

    public TreeNode(T data){
        this.data = data;
    }

    public TreeNode(T data, TreeNode parent){
        this(data);
        this.parent = parent;
    }

    public TreeNode getRoot(){
        TreeNode tmp = this;
        while (tmp.getParent() != null){
            tmp = tmp.getParent();
        }
        return tmp;
    }

    @Override
    public String toString(){
        return getData().toString()+":"+getHeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode<?> treeNode = (TreeNode<?>) o;
        return Objects.equal(data, treeNode.data);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(data);
    }
}
