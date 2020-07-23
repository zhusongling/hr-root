package com.ling.hr.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DataEntity extends DataObject {
    private Integer rowStatus; // 行状态
    private String rowCreator; // 行创建人
    private String rowCreateTime; // 行创建时间
    private String rowCreateInstId; // 行创建实例ID
    private String rowUpdater; // 行修改人
    private String rowUpdateTime; // 行修改时间
    private String rowUpdateInstId; // 行修改实例ID
}
