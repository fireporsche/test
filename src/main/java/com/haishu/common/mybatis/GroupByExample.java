package com.haishu.common.mybatis;

public abstract class GroupByExample<T> {

    private String[] groupBy;

    public T groupBy(String... column) {
        if (null == column) {
            throw new RuntimeException("分组字段不能null");
        }
        groupBy = column;
        return (T) this;
    }

    private Integer pageNum;

    private Integer pageSize;

    private Integer offset;

    public T startPage(Integer pageNum, Integer pageSize) {
        if (null == pageNum || null == pageSize) return (T) this;
        if (pageNum <= 0) {
            this.pageNum = 1;
        }
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.offset = (pageNum - 1) * pageSize;
        return (T) this;
    }
}
