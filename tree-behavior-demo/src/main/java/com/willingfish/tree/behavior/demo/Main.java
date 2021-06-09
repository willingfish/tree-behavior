package com.willingfish.tree.behavior.demo;

import com.willingfish.tree.behavior.core.Bootstrap;
import com.willingfish.tree.behavior.demo.tree.RBTree;

public class Main {

    public static void main(String[] args){
        Bootstrap bootstrap = Bootstrap.builder().registerTree(RBTree.class).build();
        bootstrap.start();
    }
}
