package com.ling.hr.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class DataGrid<E> extends DataObject {
    /**
     * 总记录数
     */
    private Long totalCount = 0L;
    /**
     * 单页数据
     */
    private List<E> dataList = new ArrayList<E>();

    private DataGrid() {

    }

    public static <E> DataGrid<E> newInstance() {
        return new DataGrid<E>();
    }
}
