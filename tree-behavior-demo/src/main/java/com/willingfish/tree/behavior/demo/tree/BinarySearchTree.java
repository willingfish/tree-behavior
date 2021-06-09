package com.willingfish.tree.behavior.demo.tree;


import java.util.Objects;

public class BinarySearchTree<T extends Comparable> extends BinaryTree<T> {

    protected TreeNode<T> _hot;
    private int size = 0;

    @Override
    public TreeNode<T> insert(T t) {
        if (Objects.nonNull(search(t))){
            return null;
        }

        TreeNode node = createNode(t);
        if (Objects.isNull(root)){
            root = node;
            return root;
        }

        if (_hot.getData().compareTo(t)<0){
            node.setParent(_hot);
            _hot.setRight(node);
        }else {
            node.setParent(_hot);
            _hot.setLeft(node);
        }
        size++;
        return node;
    }


    @Override
    public void clear() {
        root = null;
    }

    @Override
    public TreeNode<T> delete(T t) {
        TreeNode<T> node = search(t);
        if (Objects.isNull(node)){
            return null;
        }

        TreeNode<T> candidate;
        if (Objects.nonNull(node.getLeft())&& Objects.nonNull(node.getRight())){
            candidate = getMostLeftDescendant(node);
            T tmp = node.getData();
            node.setData(candidate.getData());
            candidate.setData(tmp);
        }else {
            candidate=node;
        }
        doDeleteNode(candidate);
        size--;
        TreeNode returnVal = candidate.getParent();
        return returnVal;
    }


    private void doDeleteNode(TreeNode node){
        if (Objects.nonNull(node.getRight())){
            TreeNode child = node.getRight();
            if (isLeftChild(node)){
                node.getParent().setLeft(child);
            }else {
                node.getParent().setRight(child);
            }
            child.setParent(node.getParent());
        }else if (Objects.nonNull(node.getLeft())){
            TreeNode child = node.getLeft();
            if (isLeftChild(node)){
                node.getParent().setLeft(child);
            }else {
                node.getParent().setRight(child);
            }
            child.setParent(node.getParent());
        }else {
            if (node == root){
                root =null;
            }else if (isLeftChild(node)){
                node.getParent().setLeft(null);
            }else {
                node.getParent().setRight(null);
            }
        }
    }

    protected boolean isLeftChild(TreeNode node){
        if (node == root){
            return false;
        }

        return node.getParent().getLeft()==node;
    }


    private TreeNode getMostLeftDescendant(TreeNode treeNode){
        TreeNode result = treeNode.getRight();
        while (Objects.nonNull(result.getLeft())){
            result = result.getLeft();
        }
        return result;
    }



    public TreeNode search(T t){
        if (Objects.isNull(root)){
            return null;
        }
        TreeNode<T> result = _hot = root;
        while (Objects.nonNull(result)){
            if (result.getData().equals(t)){
                return result;
            }else if (result.getData().compareTo(t) < 0){
                _hot = result;
                result = _hot.getRight();
            }else {
                _hot = result;
                result = _hot.getLeft();
            }
        }
        return null;
    }
    protected TreeNode rotateAt(TreeNode node){
        TreeNode p = node.getParent();
        TreeNode g = p.getParent();

        if (isLeftChild(p)){
            if (isLeftChild(node)){
                p.setParent(g.getParent());
                return connect34(node,p,g,node.getLeft(),node.getRight(),p.getRight(),g.getRight());
            }else {
                node.setParent(g.getParent());
                return connect34(p,node,g,p.getLeft(),node.getLeft(),node.getRight(),g.getRight());
            }
        }else {
            if (isLeftChild(node)){
                node.setParent(g.getParent());
                return connect34(g,node,p,g.getLeft(),node.getLeft(),node.getRight(),p.getRight());
            }else {
                p.setParent(g.getParent());
                return connect34(g,p,node,g.getLeft(),p.getLeft(),node.getLeft(),node.getRight());
            }
        }
    }
    protected TreeNode connect34(TreeNode a,TreeNode b,TreeNode c
            ,TreeNode t1,TreeNode t2,TreeNode t3,TreeNode t4){
        a.setLeft(t1);
        if (Objects.nonNull(t1)){
            t1.setParent(a);
        }
        a.setRight(t2);
        if (Objects.nonNull(t2)){
            t2.setParent(a);
        }
        updateHeight(a);

        c.setLeft(t3);
        if (Objects.nonNull(t3)){
            t3.setParent(c);
        }
        c.setRight(t4);
        if (Objects.nonNull(t4)){
            t4.setParent(c);
        }
        updateHeight(c);


        b.setLeft(a);a.setParent(b);
        b.setRight(c);c.setParent(b);
        updateHeight(b);
        if(Objects.isNull(b.getParent())){
            root = b;
        }
        return b;
    }
    protected void updateHeight(TreeNode node){
        Integer newHeight = Math.max(getHeight(node.getLeft()),getHeight(node.getRight()))+1;
        node.setHeight(newHeight);
    }
    protected Integer getHeight(TreeNode node){
        if (Objects.isNull(node)){
            return -1;
        }
        return node.getHeight();
    }


    protected TreeNode tallerChild(TreeNode node){
        return getHeight(node.getLeft())>getHeight(node.getRight())?node.getLeft():
                getHeight(node.getLeft())<getHeight(node.getRight())?node.getRight():
                        isLeftChild(node)?node.getLeft():node.getRight();

    }
    protected void setChild(TreeNode p,TreeNode n,boolean isLeft){
        if (Objects.nonNull(p)){
            if (isLeft){
                p.setLeft(n);
            }else {
                p.setRight(n);
            }
        }
    }
}
