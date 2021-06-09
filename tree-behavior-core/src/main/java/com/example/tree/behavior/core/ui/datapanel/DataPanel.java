package com.example.tree.behavior.core.ui.datapanel;

import com.example.tree.behavior.core.api.IShowTree;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Point2D;
import java.util.Objects;

public class DataPanel extends JPanel {

    TreeFactory treeFactory = new TreeFactory();

    InternalTree internalTree;
    public void refreshTree(IShowTree tree){
        Graphics g = this.getGraphics();
        Graphics2D g2 = (Graphics2D) g;
        internalTree = treeFactory.generate(tree);
        internalTree.preOrderTraversal(node->drawNode(g2,node));
    }

    private void drawLineBetweenNodes(Graphics2D g2, InternalNode nodeBean){
        if (Objects.isNull(nodeBean.getParent())){
           return;
        }

        Integer offsetX= InternalNode.WIDTH>>>1;
        Integer offsetY = InternalNode.HEIGHT;
        InternalNode parentBean = nodeBean.getParent();

        g2.drawLine(parentBean.getX()+offsetX,parentBean.getY()+offsetY,nodeBean.getX()+offsetX,nodeBean.getY());
    }

    private void drawNode(Graphics2D g2, InternalNode internalNode){
        g2.setColor(internalNode.getData().isRed()?Color.red:Color.blue);
        g2.drawRect(internalNode.getX(), internalNode.getY(), InternalNode.WIDTH, InternalNode.HEIGHT);

        g2.setColor(Color.black);

        {
            Point2D loc = new Point(internalNode.getX()+10, internalNode.getY()+13);
            Font font = g2.getFont();
            FontRenderContext frc = g2.getFontRenderContext();
            TextLayout layout = new TextLayout(internalNode.getData().getData().toString(), font, frc);
            layout.draw(g2, (float)loc.getX(), (float)loc.getY());
        }

        drawLineBetweenNodes(g2,internalNode);

    }
}
