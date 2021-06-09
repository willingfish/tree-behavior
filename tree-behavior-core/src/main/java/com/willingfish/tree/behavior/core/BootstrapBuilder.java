package com.willingfish.tree.behavior.core;

import com.willingfish.tree.behavior.core.api.IShowTree;

public class BootstrapBuilder {

    Bootstrap bootstrap;

    BootstrapBuilder(){
        bootstrap = new Bootstrap();
    }


    public BootstrapBuilder registerTree(Class<? extends IShowTree> tree){
        bootstrap.setTree(tree);
        return this;
    }

    public Bootstrap build(){
        return bootstrap;
    }
}
