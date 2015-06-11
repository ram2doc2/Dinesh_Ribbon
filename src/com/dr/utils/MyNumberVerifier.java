/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dr.utils;
import java.math.BigDecimal;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Sys-01
 */
public class MyNumberVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        boolean flag=true;
        String text = ((JTextField) input).getText();
        if (!text.isEmpty())
        {
        try {
            BigDecimal value = new BigDecimal(text);
            return (value.scale() <= Math.abs(2));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Invalid Input! Please Enter Decimal Numbers",
                        "Dialog", JOptionPane.PLAIN_MESSAGE);
            flag = false;
        }
        }
        return flag;
    }
}