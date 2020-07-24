package com.work.entity;

import lombok.Data;

@Data
public class ImageInfo {
    /**
     * 文件名称
     */
    private String name;
    /**
     * 返回hash
     */
    private String hash;
    /**
     * 文件大小
     */
    private String size;
}
