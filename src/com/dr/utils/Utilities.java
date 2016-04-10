/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dr.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Dinesh
 */
public class Utilities {
    
    public static String getCurrentStadardDate() {
        return getCurrentStadardDate("dd-MM-yyyy");
    }
    
    public static String getCurrentStadardDate(String format) {
        Date date = new Date();
        DateFormat showFormat = new SimpleDateFormat(format);
        return showFormat.format(date);
    }
}
