package com.xendit.helper;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropertiesHelper {

    /**
     * Reade data in properties file
     * @param sFile
     * @param sKey
     * @return
     */
    public static String getConfigValue(String sFile, String sKey){
        Properties prop = new Properties();
        String sValue = null;
        try {
            FileInputStream input = new FileInputStream(new File(sFile));
            prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));
            sValue = prop.getProperty(sKey);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sValue;
    }

    /**
     * Set data in properties file
     * @param sFile
     * @param sKey
     * @param sValue
     */
    public static void setConfigValue(String sFile, String sKey, String sValue){
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream(new File(sFile));
            prop.load(fis);
            fis.close();

            FileOutputStream fos = new FileOutputStream(new File(sFile));
            prop.setProperty(sKey, sValue);
            prop.store(fos, "Updating folder path");
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
