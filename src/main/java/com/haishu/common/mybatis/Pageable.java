package com.haishu.common.mybatis;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页查询参数
 * 2016.11.23
 */
public class Pageable {
    public static final int PAGE_INDEX = 1;
    public static final int PAGE_SIZE = 10;
    public static final String STR_PAGE_INDEX = "1";
    public static final String STR_PAGE_SIZE = "10";
    public static final int MAX_DOWNLOAD_SIZE = 50000;

    public static final int MAX_PAGE_SIZE = 1000;
    public static final int PER_PAGE_MAX_SIZE = 100;

    /**
     * 页码
     */
    private int page;

    /**
     * 每页条数
     */
    private int size;

    /**
     * 针对mysql，每页的起始序号，从0开始
     */
    private int offset;

    private Sort sort;

    private List<Sort> sorts;

    /**
     * 查询条件。
     * 在SQL映射文件中需要自行根据Key来判断where子句方式。
     */
    private Map<String, Object> params = new HashMap<>();

    /**
     * 总记录条数。
     * 返回数据，由PageInterceptor设置。
     */
    private int totalElements;

    public Pageable(int page, int size) {
        this(page, size, null);
    }

    public Pageable(int page, int size, List<Sort> sorts) {
        this.page = page;
        this.size = size;
        this.sorts = sorts;
        this.offset = (page - 1) * size;
    }

    public List<Sort> getSorts() {
        return sorts;
    }

//    public Pageable(int page, int size, Sort sort) {
//        this.page = page;
//        this.size = size;
//        this.sort = sort;
//        this.offset = (page - 1) * size;
//    }

    public void setSorts(List<Sort> sorts) {
        this.sorts = sorts;
    }

    public Pageable addParam(String key, Object value) {
        params.put(key, value);

        return this;
    }

    public Pageable addSomeParam(String key, Object value) {
        if (value != null) {
            params.put(key, value);
        }

        return this;
    }

    public Pageable addSomeParam(String key, String value) {
        if (StringUtils.isNoneBlank(value)) {
            params.put(key, value);
        }

        return this;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
        this.offset = (page - 1) * size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getOffset() {
        return offset;
    }

    public Sort getSort() {
        return sort;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }
}