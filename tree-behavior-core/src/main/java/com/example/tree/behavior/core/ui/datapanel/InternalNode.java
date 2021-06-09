package com.example.tree.behavior.core.ui.datapanel;

import com.example.tree.behavior.core.api.IShowBinNode;
import lombok.Data;

import java.awt.*;

@Data
class InternalNode {
    public static final Integer WIDTH=40;
    public static final Integer HEIGHT=35;

    int x,y;
    IShowBinNode data;
    Color color ;
    int depth;

    InternalNode parent;
    InternalNode left;
    InternalNode right;


}
