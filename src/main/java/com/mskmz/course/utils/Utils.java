package com.mskmz.course.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
    public static String getBlob(InputStream in) throws IOException {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int count = -1;
        while ((count = in.read(data, 0, 4096)) != -1)
            outStream.write(data, 0, count);

        data = null;
        String result = new String(outStream.toByteArray(), "gb2312");
        return result;

    }
}
