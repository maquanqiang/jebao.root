package com.jebao.erp.web.utils.toolbox;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Lee on 2016/11/25.
 */
public class BetweenDays {
    public static int differentDays(Date date1,Date date2)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println(sdf.format(date1)+"----"+sdf.format(date2));
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2) //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0) //闰年
                {
                    timeDistance += 366;
                }
                else //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) ;
        }
        else //不同年
        {
            //System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }
}
