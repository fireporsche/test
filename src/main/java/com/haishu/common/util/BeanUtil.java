package com.haishu.common.util;

import java.util.*;

/**
 * BeanUtils
 * @author zhb
 * @date 2019/07/08
 */
public class BeanUtil {
    /**
     * 转换
     * @param from
     * @param to
     * @param <T>
     * @return
     */
    public static <T> T chang(Object from, Class<T> to) {
        if (from == null) {
            return  null;
        } else {
            return JsonUtil.fromJson(JsonUtil.toJson(from), to);
        }
    }

    /**
     * 转换
     * @param fromList
     * @param to
     * @param <T>
     * @return
     */
    public static <T> List<T> changList(List<?> fromList, Class<T> to) {
        if (fromList == null && fromList.isEmpty()) {
            return  null;
        } else {
            List<T> list = new ArrayList<>();
            for (Object from : fromList) {
                list.add(JsonUtil.fromJson(JsonUtil.toJson(from), to));
            }
            return list;
        }
    }
}