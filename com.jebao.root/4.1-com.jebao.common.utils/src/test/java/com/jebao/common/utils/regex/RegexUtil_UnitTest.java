package com.jebao.common.utils.regex;

import org.junit.Test;

/**
 * Created by Administrator on 2016/10/19.
 */
public class RegexUtil_UnitTest {
    @Test
    public void example()
    {
        System.out.println(RegexUtil.matches("2016", "\\d{4}"));
        System.out.println(RegexUtil.matches("2016-03-15 20:50:00", "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$"));
    }
}
