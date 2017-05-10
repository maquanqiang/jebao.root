package com.jebao.thirdPay.fuiou.util;

import org.apache.commons.httpclient.NameValuePair;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Logger;
@SuppressWarnings("unchecked")
public class RequestData
{

    public static String getSortJoinParamsStr(Object parameters)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        List list = getNameValuePair(parameters);
        return getSrcRegValue(list);
    }

    private static String getSrcRegValue(List list)
    {
        Map map = new HashMap();
        NameValuePair pair;
        for(Iterator iterator = list.iterator(); iterator.hasNext(); map.put(pair.getName(), pair.getValue()))
            pair = (NameValuePair)iterator.next();

        return getSortMap(map);
    }

    private static String getSortMap(Map map)
    {
        StringBuilder sb = new StringBuilder();
        List mappingList = new ArrayList(map.entrySet());
        Collections.sort(mappingList, new Comparator<Map.Entry<String,String>>(){
            public int compare(Map.Entry<String,String> mapping1,Map.Entry<String,String> mapping2){
                return mapping1.getKey().compareTo(mapping2.getKey());
            }
        });


        java.util.Map.Entry mapping;
        for(Iterator iterator = mappingList.iterator(); iterator.hasNext(); sb.append((String)mapping.getValue()).append("|"))
            mapping = (java.util.Map.Entry)iterator.next();

        Logger.getLogger(sb.toString());
        System.out.println(sb.toString().substring(0, sb.toString().length() - 1));
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    private static List getNameValuePair(Object parameters)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        List list = new ArrayList();
        Method ms[] = parameters.getClass().getMethods();
        for(int i = 0; i < ms.length; i++)
        {
            Method m = ms[i];
            String name = m.getName();
            if(name.startsWith("get"))
            {
                String param = name.substring(3, name.length());
                param = (new StringBuilder(String.valueOf(param.substring(0, 1).toLowerCase()))).append(param.substring(1, param.length())).toString();
                if(!param.equals("class"))
                {
                    Object value = "";
                    value = m.invoke(parameters, null);
                    NameValuePair nvp = new NameValuePair(param, value == null?"":value.toString());
                    list.add(nvp);
                }
            }
        }

        return list;
    }
}