package com.work.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 文件hash
     */
    private String hash;
}
