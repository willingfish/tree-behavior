package com.example.tree.behavior.demo.tree;

import java.util.Objects;

public class AVLTree<T extends Comparable> extends BinarySearchTree<T> {

    @Override
    public TreeNode<T> insert(T t) {
        TreeNode node = super.insert(t);

        for (TreeNode cur=_hot;Objects.nonNull(cur);cur=cur.getParent()){
            if (isBalance(cur)){
                updateHeight(cur);
            }else {
                TreeNode parent = cur.getParent();
                boolean isLeftChild = isLeftChild(cur);
                TreeNode node1=rotateAt(tallerChild(tallerChild(cur)));
                setChild(parent,node1,isLeftChild);
                break;
            }
        }
        return node;
    }

    @Override
    public TreeNode<T> delete(T t) {
        TreeNode node = super.delete(t);

        for (TreeNode cur=node;Objects.nonNull(cur);cur=cur.getParent()){
            if (!isBalance(cur)){
                TreeNode parent = cur.getParent();
                boolean isLeftChild = isLeftChild(cur);
                TreeNode node1=rotateAt(tallerChild(tallerChild(cur)));
                setChild(parent,node1,isLeftChild);
            }else {
                updateHeight(cur);
            }
        }
        return node;
    }




    private boolean isBalance(TreeNode node){
        return Math.abs(getHeight(node.getLeft())-getHeight(node.getRight()))<2;
    }
}
