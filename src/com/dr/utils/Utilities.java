/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dr.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    
    /**
     * Financial year according to current date & time.
     * 
     * @return comma separated years, e.g 2016, 2017
     */
    public static String getCurrentFinancialYear() {
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int fiscalYear = cal.get(Calendar.MONTH) < 3 ? currentYear - 1 : currentYear; 
        System.out.println("Financial Year : " + fiscalYear + "," + (fiscalYear + 1));
        
        return fiscalYear + "," + (fiscalYear + 1);
    }
    
    public static void main(String[] args) throws ParseException {
        getCurrentFinancialYear();
    }
}
