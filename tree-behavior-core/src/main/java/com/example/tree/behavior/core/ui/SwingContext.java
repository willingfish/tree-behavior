package com.example.tree.behavior.core.ui;

import com.example.tree.behavior.core.api.IShowTree;
import com.example.tree.behavior.core.ui.datapanel.DataPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class SwingContext {

    IShowTree tree;
    ExecutorService executorService = Executors.newFixedThreadPool(5);

    public SwingContext(Class<? extends IShowTree> treeClass) throws InstantiationException, IllegalAccessException {
        tree = treeClass.newInstance();
    }

    public void init() {

        DataPanel dataPanel = new DataPanel();
        OptionPanel optionPanel = new OptionPanel();
        ActionListener addListener = (e) -> {
            tree.insert(optionPanel.getInputValue());

            executorService.submit(() -> {
                try {
                    SwingUtilities.invokeAndWait(() -> dataPanel.repaint());
                    SwingUtilities.invokeLater(() -> dataPanel.refreshTree(tree));
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                } catch (InvocationTargetException invocationTargetException) {
                    invocationTargetException.printStackTrace();
                }
            });
        };
        ActionListener deleteListener = (e) -> {
            executorService.submit(() -> {
                try {
                    tree.delete(optionPanel.getInputValue());
                    SwingUtilities.invokeAndWait(() -> {
                        dataPanel.repaint();
                    });
                    SwingUtilities.invokeLater(() -> {
                        dataPanel.refreshTree(tree);
                    });
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                } catch (InvocationTargetException invocationTargetException) {
                    invocationTargetException.printStackTrace();
                }
            });
        };
        ActionListener reloadListener = (e) -> {
            executorService.submit(() -> {
                try {
                    tree.clear();
                    Random random = new Random();
                    random.ints(20, 0, 500)
                            .forEach(i -> tree.insert(i));

                    SwingUtilities.invokeAndWait(() -> {
                        dataPanel.repaint();
                    });
                    SwingUtilities.invokeLater(() -> {
                        dataPanel.refreshTree(tree);
                    });
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                } catch (InvocationTargetException invocationTargetException) {
                    invocationTargetException.printStackTrace();
                }
            });
        };

        dataPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        dataPanel.setBackground(Color.decode("#D3D3D3"));

        JTextField input = new JTextField(16);
        JButton addButton = new JButton("Add");
        JButton rmButton = new JButton("Delete");
        JButton reloadButton = new JButton("Reload");
        JButton nextButton = new JButton("Next");
        addButton.addActionListener(addListener);
        rmButton.addActionListener(deleteListener);
        reloadButton.addActionListener(reloadListener);
        optionPanel.add(input);
        optionPanel.add(addButton);
        optionPanel.add(rmButton);
        optionPanel.add(reloadButton);
        optionPanel.add(nextButton);
        optionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        optionPanel.setInput(input);

        JFrame showFrame = new JFrame("Show Tree Process");
        showFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        showFrame.setLayout(new BorderLayout());
        showFrame.add(optionPanel, BorderLayout.NORTH);
        showFrame.add(dataPanel, BorderLayout.CENTER);
        showFrame.setMinimumSize(new Dimension(SwingConsts.FRAME_WIDTH, SwingConsts.FRAME_HEIGHT));
        showFrame.setVisible(true);
        showFrame.pack();
    }
}
