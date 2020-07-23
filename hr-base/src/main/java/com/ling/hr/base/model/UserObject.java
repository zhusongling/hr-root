package com.ling.hr.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserObject extends DataObject {
    private String userId; // 用户ID
    private String userCode; // 用户编码
    private String userName; // 用户名称
    private String loginName; // 登录名
    private String authToken; // Auth_Token
    private String nickName;
    private String loginPwd;
    private String headImgUrl;
    private String phone;
    private String userType;
    private String subjectType;
    private String birthday;
    private String appInstId;
}
