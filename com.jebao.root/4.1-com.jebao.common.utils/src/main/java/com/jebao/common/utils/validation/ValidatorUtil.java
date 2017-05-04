package com.jebao.common.utils.validation;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by Jack on 2016/12/12.
 */
public class ValidatorUtil {
    public boolean isMobile(String input){
        return Pattern.matches("^1(3|4|5|7|8)\\d{9}$",input);
    }
    /**
     * 身份证号校验
     */
    public boolean isIdCard(String input){
        if (StringUtils.isBlank(input)){
            return false;
        }
        String value = input.toUpperCase().trim();
        String regex = "^[1-9]\\d{5}[1-9]\\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2]\\d)|(3[0-1]))\\d{3}[xX\\d]$";

        if (! Pattern.matches(regex,input)) {
            return false;
        }
        String cityCodeJoins = "11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91";
        if (cityCodeJoins.indexOf(value.substring(0,2)) == -1) {
            return false;
        }
        else {
            //18位身份证需要验证最后一位校验位
            // ∑(ai×Wi)(mod 11)
            //加权因子
            int[] factor = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
            //校验位
            char[] parity = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
            int sum = 0;
            int ai = 0;
            int wi = 0;
            for (int i = 0; i < 17; i++)
            {
                ai = Integer.parseInt(value.substring(i,i+1));
                wi = factor[i];
                sum += ai * wi;
            }
            char last = parity[sum % 11];
            if (last != value.charAt(17))
            {
                return false;
            }
        }
        return true;
    }
}
