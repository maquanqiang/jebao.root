package com.jebao.thirdPay.fuiou.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.SimpleTimeZone;

/**
 * Created by Lee on 2016/12/10.
 */
public class Common {
    //返回随机数
    public static String getRandomNum(int length){
        try
        {
            if (length <= 0)
            {
                return "";
            }
            Random r = new Random();
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < length; i++)
            {
                result.append(Integer.toString(r.nextInt(10)));
            }
            return result.toString();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }


    public static String mchntTxnSsn(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return getRandomNum(3)+sdf.format(new Date());
    }
}
