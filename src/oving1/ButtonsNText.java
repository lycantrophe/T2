/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oving1;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author lycantrophe
 */
public class ButtonsNText extends JPanel {

    private JTextField TextLine;
    private JToggleButton UpperCaseButton;
    private JToggleButton LowerCaseButton;
    private JCheckBox ContinuousButton;
    private MyActionListener al;
    // private MyDocListener dl;
    private Textmod textmod;
    private boolean tUpper = false;

    public ButtonsNText() {
        // Add elemnts to panel

        textmod = new Textmod();
        al = new MyActionListener();
        KeyListener kl = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {
                if (ContinuousButton.isSelected()) {
                    int caret = TextLine.getCaretPosition();
                    String text = TextLine.getText();
                    text = tUpper == true ? text.toUpperCase() : text.toLowerCase();
                    TextLine.setText(text);
                    TextLine.setCaretPosition(caret);
                }
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                // don't care           
                if (ContinuousButton.isSelected()) {
                    int caret = TextLine.getCaretPosition();
                    String text = TextLine.getText();
                    text = tUpper == true ? text.toUpperCase() : text.toLowerCase();
                    TextLine.setText(text);
                    TextLine.setCaretPosition(caret);
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                // don't care
                if (ContinuousButton.isSelected()) {
                    int caret = TextLine.getCaretPosition();
                    String text = TextLine.getText();
                    text = tUpper == true ? text.toUpperCase() : text.toLowerCase();
                    TextLine.setText(text);
                    TextLine.setCaretPosition(caret);
                }
            }
        };
        //     dl = new MyDocListener();

        TextLine = new JTextField("");
        TextLine.setName("TextLine");
        TextLine.addActionListener(al);
        TextLine.addKeyListener(kl);
        TextLine.setPreferredSize(new Dimension(100, 24));
        // TextLine.getDocument().addDocumentListener(dl);
        add(TextLine);

        UpperCaseButton = new JToggleButton("Upper case");
        UpperCaseButton.setName("UpperCaseButton");
        UpperCaseButton.addActionListener(al);
        add(UpperCaseButton);

        LowerCaseButton = new JToggleButton("Lower case");
        LowerCaseButton.setName("LowerCaseButton");
        LowerCaseButton.addActionListener(al);
        add(LowerCaseButton);

        ContinuousButton = new JCheckBox("Continuous?");
        ContinuousButton.setName("ContinuousButton");
        ContinuousButton.addActionListener(al);
        add(ContinuousButton);

    }

    private class MyDocListener implements DocumentListener {

        @Override
        public void changedUpdate(DocumentEvent e) {
        }

        public void removeUpdate(DocumentEvent e) {
            textmod.setTextField(TextLine.getText());
            textmod.changeCase();
            TextLine.setText(textmod.getTextField());
        }

        public void insertUpdate(DocumentEvent e) {
            textmod.setTextField(TextLine.getText());
            textmod.changeCase();
            TextLine.setText(textmod.getTextField());
        }
    }

    private class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == UpperCaseButton) {

                TextLine.setText(TextLine.getText().toUpperCase());
                tUpper = true;

            } else if (ae.getSource() == LowerCaseButton) {

                TextLine.setText(TextLine.getText().toLowerCase());
                tUpper = false;
            } else if (ae.getSource() == TextLine) {

                String text = TextLine.getText();
                text = tUpper == true ? text.toUpperCase() : text.toLowerCase();
                TextLine.setText(text);
            }
        }
    }

    private class Textmod {

        private boolean tUpper;
        private boolean continuous;
        private String textField;

        Textmod() {
            tUpper = false;
            continuous = false;
            textField = "";
        }

        // Takes boolean value. True if upper, false if lower.
        public void casePressed(boolean upper) {
            if (upper) {
                tUpper = true;
            } else {
                tUpper = false;
            }
            this.changeCase();
        }

        public void contPressed() {
            continuous = !continuous;
        }

        public void changeCase() {
            if (tUpper) {
                textField.toUpperCase();
            } else {
                textField.toLowerCase();
            }
        }

        /**
         * @return the textField
         */
        public String getTextField() {
            return textField;
        }

        /**
         * @param textField the textField to set
         */
        public void setTextField(String text) {
            this.textField = text;
        }
    }

    public static void main(String[] args) {
        JFrame pane = new JFrame();
        ButtonsNText btn = new ButtonsNText();
        pane.add(btn);
        pane.pack();
        pane.setVisible(true);
    }
}
