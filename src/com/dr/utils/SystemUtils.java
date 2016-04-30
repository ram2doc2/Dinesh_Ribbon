/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dr.utils;

/**
 *
 * @author Ram
 */
public class SystemUtils {

    private static final String OS_NAME = System.getProperty("os.name").toLowerCase();

    private static final String OS_VERSION = System.getProperty("os.version").toLowerCase();
    
    public static void main(String[] args) {

        System.out.println(OS_NAME);
        
        System.out.println(OS_VERSION);

        if (isWindows()) {
            System.out.println("This is Windows");
        } else if (isMac()) {
            System.out.println("This is Mac");
        System.out.println(OS_NAME);
        } else if (isUnix()) {
            System.out.println("This is Unix or Linux");
        } else if (isSolaris()) {
            System.out.println("This is Solaris");
        } else {
            System.out.println("Your OS is not support!!");
        }
    }

    public static boolean isWindows() {
        return (OS_NAME.contains("win"));
    }

    public static boolean isMac() {
        return (OS_NAME.contains("mac"));
    }

    public static boolean isUnix() {
        return (OS_NAME.contains("nix") || OS_NAME.contains("nux") || OS_NAME.indexOf("aix") > 0 );
    }

    public static boolean isSolaris() {
        return (OS_NAME.contains("sunos"));
    }

    public static float getVersion() {
        return Float.valueOf(OS_VERSION);
    }
}