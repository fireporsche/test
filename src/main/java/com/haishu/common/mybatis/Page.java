package com.haishu.common.mybatis;

import java.util.List;

/**
 * 分页查询结果
 * 2016.11.23
 */
public class Page<T> {

    private int page;
    private int size;
    private int totalElements;
    private int totalPage;
    private List<T> content;
    /*
    *  此字段可以定义不同条件下的总数
    *  例如一个查询需要知道条件1的总数和条件2的总数
    * */
    private int totalElementsCondition1;

    public Page(Pageable pageable, List<T> content) {
        this.page = pageable.getPage();
        this.size = pageable.getSize();
        this.totalElements = pageable.getTotalElements();
        this.content = content;
        this.totalPage = (pageable.getTotalElements() + pageable.getSize() - 1) / pageable.getSize();
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getTotalElementsCondition1() {
        return totalElementsCondition1;
    }

    public void setTotalElementsCondition1(int totalElementsCondition1) {
        this.totalElementsCondition1 = totalElementsCondition1;
    }
}