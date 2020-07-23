package com.ling.hr.base.enums;

/**
 * 发送邮件类型
 */
public enum MialType {
    // 邮件类型【1=文本形式；2=HTML形式；3=附件形式；4=图片形式；5=模板形式】
    Text(1), Html(2), Attach(3), Pic(4), Template(5);

    private Integer value;

    MialType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
