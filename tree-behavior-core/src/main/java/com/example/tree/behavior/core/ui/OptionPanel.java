package com.example.tree.behavior.core.ui;

import javax.swing.*;

public class OptionPanel extends JPanel {

    JTextField input;

    public void setInput(JTextField input) {
        this.input = input;
    }

    public Integer getInputValue(){
        Integer inputValue = Integer.parseInt(input.getText());
        return inputValue;
    }

}
