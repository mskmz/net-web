package com.mskmz.course.utils;

/**
 * Created by mskmz on 2017/8/1.
 */
public class Utils {
    public static  boolean checkString(String number){
        try{
            if(new Integer(number)>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
}
