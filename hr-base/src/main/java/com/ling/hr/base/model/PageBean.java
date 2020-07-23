package com.ling.hr.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PageBean extends DataObject {
    public static final Integer DEFAULT_PAGE_INDEX = 1;
    public static final Integer DEFAULT_PAGE_SIZE = 1000;
    /**
     * 页码
     */
    private int pageIndex = 1;

    /**
     * 每页条数
     */
    private int pageSize = 10;
    /**
     * 排序
     */
    private String orderBy;

    /**
     * 分页起始索引
     */
    private int startIndex;

    public PageBean() {

    }

    public PageBean(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    /**
     * 获取分页开始的位置
     *
     * @return
     */
    public Integer getStartIndex() {
        if (pageIndex < 1) {
            return 0;
        }
        return (pageIndex - 1) * pageSize;
    }
}
