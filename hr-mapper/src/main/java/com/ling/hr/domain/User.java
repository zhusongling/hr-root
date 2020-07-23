package com.ling.hr.domain;

import com.ling.hr.base.model.DataEntity;
import lombok.Data;

@Data
public class User extends DataEntity {
    private String userId; // 用户ID
    private String deptId; // 部门ID
    private String jobId; // 岗位ID
    private String loginName; // 登陆名
    private String loginPwd; // 登陆密码
    private String nickName; // 用户昵称
    private String email; // 邮箱地址
    private String phone; // 手机号
    private String headImage; // 头像
}
