package com.jebao.common.utils.map;

import java.util.Map;

/**
 * Created by Administrator on 2016/12/16.
 */
public class MapUtil {
    public static <K, V> V getFirstOrNull(Map<K, V> map) {
        V obj = null;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            obj = entry.getValue();
            if (obj != null) {
                break;
            }
        }
        return obj;
    }
}
