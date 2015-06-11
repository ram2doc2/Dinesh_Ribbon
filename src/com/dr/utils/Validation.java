/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dr.utils;

import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Ram Doctor
 */
public class Validation {
      public void decimalValidator(KeyEvent evt,JTextField textfield,int x,int y)
    {
       
        Pattern pattern2= Pattern.compile("^-?\\d{0,"+x+"}(?>\\.\\d{0,"+y+"})?$");

        Character ch=evt.getKeyChar();
        if(Character.isDigit(ch)||ch==127||ch==46||Character.isISOControl(ch)||evt.isActionKey()||evt.isAltDown()||evt.isControlDown()||evt.isShiftDown())
        {
                
                Matcher matcher2 = pattern2.matcher(textfield.getText());
                if(!(matcher2.matches()))
                {
                     JOptionPane.showMessageDialog(null,"Invalid Input\nonly "+x+" digit allowed before Decimal Point(.) AND \nonly "+y+" digit allowed after Decimal point(.)");
                     textfield.setText("");
                }

        }
        else 
        {
        JOptionPane.showMessageDialog(null,"Enter Only Numbers");
        textfield.setText("");
        
        }
    }
    
    public void integerValidator(KeyEvent evt,JTextField textfield,int n)
    {
        Pattern pattern = Pattern.compile ("(\\d{"+(n+1)+"})");
        
        Character ch=evt.getKeyChar();
        if(Character.isDigit(ch)||ch==127||Character.isISOControl(ch)||evt.isActionKey()||evt.isAltDown()||evt.isControlDown()||evt.isShiftDown())
        {
                Matcher matcher1 = pattern.matcher(textfield.getText());
                if(textfield.getText().trim().length()>(n) || matcher1.matches())
                {
                        JOptionPane.showMessageDialog(null,"Invalid Input \nOnly "+n+" digit allowed");
                        textfield.setText("");
                        
                }
               

        }
        else 
        {
        JOptionPane.showMessageDialog(null,"Enter Only Numbers");
        textfield.setText("");
        
        }
    }
    
    
 public void varcharValidator(KeyEvent evt,JTextField textfield,int n)
    {
        
        Pattern pattern = Pattern.compile (".[^(!@#$%&*()+=|'?''/''|'_<>)]{0,"+n+"}");
        
         Character ch=evt.getKeyChar();
         //System.out.println(ch.toString());
        if(Character.isDigit(ch)||Character.isLetter(ch)||ch==127||ch==45||ch==32||Character.isISOControl(ch)||evt.isActionKey()||evt.isAltDown()||evt.isControlDown()||(evt.getKeyCode()==KeyEvent.VK_SHIFT))
        {
        Matcher matcher1 = pattern.matcher(textfield.getText());
                if(textfield.getText().trim().length()>n)
                {
                    JOptionPane.showMessageDialog(null,"Invalid Input \n Only  "+n+"  Character allowed");
                        textfield.setText("");
                }
                else if(!(matcher1.matches()) && !(textfield.getText().equals("")))
                {
                        JOptionPane.showMessageDialog(null,"Invalid Input \n Special character not allowed");
                        textfield.setText("");
                }  
        }
        else{
            JOptionPane.showMessageDialog(null,"Invalid Input \n Special character not allowed");
           textfield.setText("");
        }
    }
 
 public void dateValidator(JTextField textfield)
 {
        Pattern pattern2= Pattern.compile("^([0-2]\\d|[3][0-1])\\-([0]\\d|[1][0-2])\\-([2][01]|[1][6-9])\\d{2}(\\s*([0]\\d|[1][0-2])(\\:[0-5]\\d){1,2})*\\s*([aApP][mM]{0,2})?$");
      
        Matcher matcher2 = pattern2.matcher(textfield.getText());
        if(!(matcher2.matches()) && !(textfield.getText().equals("")))
        {
             JOptionPane.showMessageDialog(null,"Invalid Date.\nDate Format should be (dd-MM-yyyy)");
             textfield.requestFocusInWindow();
             textfield.setText("");
             
        }
 }
 
 public void integerValidator2(KeyEvent evt,JTextField textfield,int n)
    {
        Pattern pattern = Pattern.compile ("(\\d{13})");
        
        Character ch=evt.getKeyChar();
        if(Character.isDigit(ch)||ch==127||Character.isISOControl(ch)||evt.isActionKey()||evt.isAltDown()||evt.isControlDown()||evt.isShiftDown())
        {
                Matcher matcher1 = pattern.matcher(textfield.getText());
                if(textfield.getText().trim().length()>n||matcher1.matches())
                {
                        JOptionPane.showMessageDialog(null,"Invalid Input \nOnly "+n+" digit allowed");
                        textfield.setText("");
                        
                }
          
        }
        else 
        {
        JOptionPane.showMessageDialog(null,"Enter Only Numbers");
        textfield.setText("");
        
        }
    }
 
 
 public void charValidator(KeyEvent evt,JTextField textfield,int n)
    {
        n=n+1;
        Pattern pattern = Pattern.compile ("^[a-zA-Z\\s.']+$");
        
         Character ch=evt.getKeyChar();
         System.out.println(ch.toString());
        if(Character.isLetter(ch)||ch==127||ch==46||ch==32||Character.isISOControl(ch)||evt.isActionKey()||evt.isAltDown()||evt.isControlDown()||(evt.getKeyCode()==KeyEvent.VK_SHIFT))
        {
        Matcher matcher1 = pattern.matcher(textfield.getText());
                if(textfield.getText().trim().length()>n && !(textfield.getText().equals("")))
                {
                    JOptionPane.showMessageDialog(null,"Invalid Input \n Only  "+(n-1)+"  Character allowed");
                        textfield.setText("");
                }
                else if(!(matcher1.matches()) && !(textfield.getText().equals("")))
                {
                        JOptionPane.showMessageDialog(null,"Invalid Input \n Special character not allowed");
                        textfield.setText("");
                }  
        }
        else{
            JOptionPane.showMessageDialog(null,"Invalid Input \n only character allowed");
           textfield.setText("");
        }
    }
 
    public void validateLength(KeyEvent evt, JTextField textfield, int n) {
        
        if (textfield.getText().length() > n ) {
            JOptionPane.showMessageDialog(null, "Invalid Input \n Only  " + n + "  Character allowed","Error!",JOptionPane.WARNING_MESSAGE);
            textfield.setText("");
        }
    }
}
