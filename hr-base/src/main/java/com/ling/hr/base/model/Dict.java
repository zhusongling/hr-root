package com.ling.hr.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = false)
public class Dict extends DataObject {
    private String id; // configKey
    private String text; // configValue
}
