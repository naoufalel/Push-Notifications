package com.naoufalel.notify.util;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by naouf on 2/14/2016.
 */
public class SecondsToStandard {

    private static String result="";

    public static String convertSecToString(int seconds) {
        int hours = seconds/ 3600;
        int remainder = seconds - hours * 3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;

        if(hours==0){
            if(mins==0){
                result = secs+"s";
            }else{
                if(secs==0)
                result = mins+"m";
                else {
                    result = mins+"m "+secs+"s";
                }
            }
        }else {
            if(secs==0){
                result = hours+"h "+mins+"m";
                if(mins==0){
                    result = hours+"h";
                }
            }else if(secs==0 && mins ==0){
                result = hours+"h";
            }else {
                result = hours+"h "+mins+"m "+secs+"s";
            }
        }

        return result;
    }

}
