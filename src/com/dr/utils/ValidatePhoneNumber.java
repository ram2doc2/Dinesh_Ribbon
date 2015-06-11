/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dr.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Sys-01
 */

public class ValidatePhoneNumber extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        boolean flag=true;
        String text = ((JTextField) input).getText();
        if (!text.isEmpty())
        {
      Pattern pattern = Pattern.compile("\\d{3}-\\d{8}");
      Matcher matcher = pattern.matcher(text);

      if (matcher.matches()) {
    	  flag = true;
      }
      else
      {
            JOptionPane.showMessageDialog(new JFrame(), "Invalid Input, Phone / Fax Number musr be in the form 999-99999999",
                        "Dialog", JOptionPane.PLAIN_MESSAGE);
            flag = false;
      }
 }
      return flag;
}
}