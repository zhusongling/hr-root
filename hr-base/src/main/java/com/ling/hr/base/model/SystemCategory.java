package com.ling.hr.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = false)
public class SystemCategory extends DataEntity {

    private String categoryId;
    private String categoryKey;
    private String remark;
}
