package com.willingfish.tree.behavior.demo.tree;

import com.willingfish.tree.behavior.core.api.IShowTree;

import java.util.Objects;

public class RBTree<T extends Comparable> extends BinarySearchTree<T> implements IShowTree<T> {

    @Override
    public RBNode<T> insert(T t) {
        RBNode node = (RBNode) super.insert(t);
        if (node == null) {
            return null;
        }
        balanceInsert(node);

        root = root.getRoot();
        return node;
    }

    @Override
    public RBNode<T> delete(T t) {
        RBNode<T> node = (RBNode<T>) super.search(t);

        RBNode<T> replace, sub;
        if (Objects.nonNull(node.getLeft())) {
            replace = node;
            sub = node.getLeft();
        } else if (Objects.nonNull(node.getRight())) {
            replace = node;
            sub = node.getRight();
        } else {
            replace = node.getRight();
            while (Objects.nonNull(replace.getLeft())) {
                replace = replace.getLeft();
            }
            sub = replace.getRight();
        }

        if (replace == root) {
            root = sub;
            sub.setRed(false);
            sub.setParent(null);
            return replace;
        }
        boolean isRed = replace.isRed();

        if (replace == node) {
            sub.setParent(node.getParent());
            if (node == node.getParent().getLeft()) {
                node.getParent().setLeft(sub);
            } else {
                node.getParent().setRight(sub);
            }
        } else if (node == replace.getParent()) {
            if (Objects.nonNull(node.getParent())) {
                if (node.getParent().getLeft() == node) {
                    node.getParent().setLeft(replace);
                } else {
                    node.getParent().setRight(replace);
                }
            }
            if (Objects.nonNull(node.getLeft())) {
                node.getLeft().setParent(replace);
            }

            replace.setParent(node.getParent());
            replace.setLeft(node.getLeft());
            replace.setRed(node.isRed());
        } else {
            if (Objects.nonNull(node.getParent())) {
                if (node.getParent().getLeft() == node) {
                    node.getParent().setLeft(replace);
                } else {
                    node.getParent().setRight(replace);
                }
            }
            if (Objects.nonNull(node.getLeft())) {
                node.getLeft().setParent(replace);
            }
            if (Objects.nonNull(node.getRight())) {
                node.getRight().setParent(replace);
            }

            replace.getParent().setLeft(replace.getRight());
            if (Objects.nonNull(replace.getRight())) {
                replace.getRight().setParent(replace.getParent());
            }

            replace.setParent(node.getParent());
            replace.setLeft(node.getLeft());
            replace.setRight(node.getRight());
            replace.setRed(node.isRed());
        }
        if (Objects.nonNull(root.getParent())) {
            while (Objects.nonNull(root.getParent())) {
                root = root.getParent();
            }
        }
        if (isRed) {
            return node;
        }

        RBNode temp = sub;
        while (temp != root) {
            if (temp.getParent().getLeft() == temp) {
                RBNode sibling = temp.getParent().getRight();
                if (sibling.isRed()) {
                    temp.getParent().setRed(true);
                    sibling.setRed(false);
                    rotateLeft(temp.getParent());
                    sibling = temp.getParent().getRight();
                }

                if (!isNodeRed(sibling.getLeft()) && !isNodeRed(sibling.getRight())) {
                    sibling.setRed(true);
                    temp.setRed(true);
                    if (temp.getParent().isRed()){
                        temp.getParent().setRed(false);
                        break;
                    }else {
                        temp = temp.getParent();
                        continue;
                    }
                }else{
                    if (isNodeRed(sibling.getLeft())){

                    }
                }

            } else {

            }
        }


        return node;
    }

    private boolean isNodeRed(RBNode node) {
        if (Objects.isNull(node)) {
            return false;
        }
        return node.isRed();
    }

    @Override
    protected RBNode createNode(T t) {
        RBNode rbNode = new RBNode(t);
        return rbNode;
    }

    private void balanceInsert(RBNode node) {
        for (RBNode x = node, xp, xpp; ; ) {
            xp = x.getParent();
            if (xp == null) {
                x.setRed(false);
                return;
            }
            if (!xp.isRed()) {
                return;
            }
            xpp = xp.getParent();
            if (xp == xpp.getLeft()) {
                if (xpp.getRight() != null && xpp.getRight().isRed()) {
                    xpp.setRed(true);
                    xp.setRed(false);
                    xpp.getRight().setRed(false);
                    x = xpp;
                } else {
                    if (x == xp.getRight()) {
                        x = xp;
                        rotateLeft(x);
                    }
                    xp = x.getParent();
                    xp.setRed(false);
                    xpp.setRed(true);
                    rotateRight(xpp);
                    return;
                }
            } else {
                if (xpp.getLeft() != null && xpp.getLeft().isRed()) {
                    xpp.setRed(true);
                    xp.setRed(false);
                    xpp.getLeft().setRed(false);
                    x = xpp;
                } else {
                    if (x == xp.getLeft()) {
                        x = xp;
                        rotateRight(x);
                    }
                    xp = x.getParent();
                    xp.setRed(false);
                    xpp.setRed(true);
                    rotateLeft(xpp);
                    return;
                }
            }
        }
    }


    private void rotateLeft(RBNode node) {
        RBNode r, p;
        r = node.getRight();
        p = node.getParent();

        node.setParent(r);
        node.setRight(r.getLeft());
        if (r.getLeft() != null) {
            r.getLeft().setParent(node);
        }

        r.setParent(p);
        r.setLeft(node);

        if (p != null) {
            if (p.getLeft() == node) {
                p.setLeft(r);
            } else {
                p.setRight(r);
            }
        }
    }

    private void rotateRight(RBNode node) {
        RBNode l, p;
        l = node.getLeft();
        p = node.getParent();

        node.setLeft(l.getRight());
        if (l.getRight() != null) {
            l.getRight().setParent(node);
        }
        node.setParent(l);

        l.setRight(node);
        l.setParent(p);

        if (p != null) {
            if (node == p.getLeft()) {
                p.setLeft(l);
            } else {
                p.setRight(l);
            }
        }
    }
}
