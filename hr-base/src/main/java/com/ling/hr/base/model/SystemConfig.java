package com.ling.hr.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = false)
public class SystemConfig extends DataEntity {

    private String configId;
    private String categoryId;
    private String categoryKey;
    private String configKey;
    private String configValue;
    private String remark;

}
