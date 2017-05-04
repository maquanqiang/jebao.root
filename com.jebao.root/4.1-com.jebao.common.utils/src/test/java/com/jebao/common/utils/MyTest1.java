package com.jebao.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jack on 2016/12/19.
 */
public class MyTest1 {
    public static void main(String[] args){
//        String password = "abc123我";
//        String md5Pass =  new EncryptUtil().encryptToMD5(password);
//        System.out.println(md5Pass);
//
//        System.out.println(md5Pass.equalsIgnoreCase("40d137d0b87281e64e54c047a65ccd2d"));

        //40d137d0b87281e64e54c047a65ccd2d

        //e10adc3949ba59abbe56e057f20f883e
        String input = "<dl><dt><font class=\"con_sub_title\">归属信息：</font>北京 - 北京</dt></dl><dl><dt><font class=\"con_sub_title\">银行名称：</font>中国工商银行</dt></dl>\n" +
                "        <dl>\n" +
                "            <dt><font class=\"con_sub_title\">银行卡名：</font>牡丹卡普卡</dt>\n" +
                "\t    \n" +
                "        </dl>\n" +
                "        <dl>\n" +
                "            \t<dt><font class=\"con_sub_title\">银行卡种：</font>借记卡</dt>\n" +
                "        </dl>\n" +
                "        <dl>\n" +
                "            \t<dt><font class=\"con_sub_title\">客服电话：</font>95588</dt>\n" +
                "        </dl>\n" +
                "\t        <dl>\n" +
                "            \t<dt><font class=\"con_sub_title\">官方网址：</font>www.icbc.com.cn</dt>\n" +
                "        </dl>\t";

        input = "<dt><font class=\"con_sub_title\">归属信息：</font>北京 - 北京</dt></dl><dl><dt><font class=\"con_sub_title\">银行名称：</font>中国工商银行</dt>";

        List<String> dlList = new ArrayList<>();

        //Pattern pattern = Pattern.compile("\\<dl\\>[\\s]+\\<dt\\>[\\S ]+</font>([\\S]+)</dt>");

        //<dl>[\s\S]+?</dl>
        //<dt><font class="con_sub_title">.*</font>[\s\S]+</dt>
        Pattern pattern = Pattern.compile("(?<=<dt><font class=\"con_sub_title\">)归属信息：(?<=</font>)[\\s\\S]+?(?=</dt>)");

        Matcher matcher = pattern.matcher(input);

        //System.out.println("匹配数量："+matcher.groupCount());

        //System.out.println(matcher.find());
        while(matcher.find()) {
            String dlItemHtml = matcher.group();
            dlList.add(dlItemHtml);
            System.out.println("匹配项："+dlItemHtml);

        }





    }


}
