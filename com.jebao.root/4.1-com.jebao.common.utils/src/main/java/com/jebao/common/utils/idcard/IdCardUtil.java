package com.jebao.common.utils.idcard;

import com.jebao.common.utils.date.DateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/17.
 */
public class IdCardUtil {
    /**
     * 根据身份编号获取性别
     *
     * @param idCard 身份编号
     * @return 性别(1-男，2-女，0-未知)
     */
    public static int getGenderByIdCard(String idCard) {
        int iGender = 0;
        idCard = idCard.trim();
        if (idCard != null && idCard.length() > 0) {
            String sCardNum = "";
            if (idCard.length() == 18) {
                sCardNum = idCard.substring(16, 17);
            } else {
                sCardNum = idCard.substring(14, 15);
            }
            if (Integer.parseInt(sCardNum) % 2 != 0) {
                iGender = 1;//男
            } else {
                iGender = 2;//女
            }
        }
        return iGender;
    }

    /**
     * 根据身份编号获取生日
     *
     * @param idCard 身份编号
     * @return 生日
     */
    public static String getBirthdayByIdCard(String idCard) {
        String sBirthday="";
        idCard = idCard.trim();
        if (idCard != null && idCard.length() > 0) {
            if (idCard.length() == 18)
            {
                sBirthday = idCard.substring(6, 10) + "-" + idCard.substring(10, 12) + "-" + idCard.substring(12, 14);
            }
            if (idCard.length() == 15)
            {
                sBirthday = "19" + idCard.substring(6, 8) + "-" + idCard.substring(8, 10) + "-" + idCard.substring(10, 12);
            }
        }
        return sBirthday;
    }

    /**
     * 根据身份编号获取年龄
     *
     * @param idCard 身份编号
     * @return 年龄
     */
    public static int getAgeByIdCard(String idCard){
        Date birthDate = DateUtil.stringToDate(getBirthdayByIdCard(idCard));
        int age = 0;
        Date now = new Date();
        SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
        SimpleDateFormat format_M = new SimpleDateFormat("MM");
        String birth_year = format_y.format(birthDate);
        String this_year = format_y.format(now);
        String birth_month = format_M.format(birthDate);
        String this_month = format_M.format(now);
        // 初步，估算
        age = Integer.parseInt(this_year) - Integer.parseInt(birth_year);
        // 如果未到出生月份，则age - 1
        if(this_month.compareTo(birth_month) < 0) {
            age -= 1;
        }
        if (age < 0) {
            age = 0;
        }
        return age;
    }
}
