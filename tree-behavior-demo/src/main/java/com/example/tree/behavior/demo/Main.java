package com.example.tree.behavior.demo;

import com.example.tree.behavior.core.Bootstrap;
import com.example.tree.behavior.demo.tree.RBTree;

public class Main {

    public static void main(String[] args){
        Bootstrap bootstrap = Bootstrap.builder().registerTree(RBTree.class).build();
        bootstrap.start();
    }
}
