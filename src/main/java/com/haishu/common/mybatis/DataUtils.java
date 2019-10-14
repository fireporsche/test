package com.haishu.common.mybatis;

import com.haishu.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.*;

/**
 * Created by yangbajing(yangbajing@gmail.com) on 2016-11-08.
 */
public class DataUtils {

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static Pageable pageable(int page, int size) {
        return new Pageable(page, size);
    }

    /**
     * @param page       页码，从1开始
     * @param size       每页限制条数
     * @param orderField 排序字段
     * @param direction  排序方式：asc, desc
     * @return
     */
    public static Pageable pageable(
            int page,
            int size,
            String orderField,
            String direction) {

        String[] orderFields = {};
        String[] directions = {};
        if (StringUtils.isNoneBlank(orderField)) {
            orderFields = orderField.split(",");
        }
        if (StringUtils.isNoneBlank(direction)) {
            directions = direction.split(",");
        }
        if (orderFields.length != directions.length) {
            throw new BusinessException(Arrays.toString(orderFields) + "有字段没有指定排序方向");
        }
        List<Sort> sorts = new ArrayList<>();
        for (int i = 0; i < orderFields.length; i++) {
            String of = orderFields[i];
            String d = directions[i];
            Sort sort = null;
            switch (Sort.Direction.valueOf(d.toUpperCase())) {
                case ASC:
                    sort = new Sort(Sort.Direction.ASC, of);
                    break;
                case DESC:
                    sort = new Sort(Sort.Direction.DESC, of);
                    break;
                case NULLSLAST:
                    sort = new Sort(Sort.Direction.NULLSLAST, of);
                    break;
                case RANDOM:
                    sort = new Sort(Sort.Direction.RANDOM, of);
                    break;
                default:break;
            }
            sorts.add(sort);
        }


        return new Pageable(page, size, sorts);
    }
}
