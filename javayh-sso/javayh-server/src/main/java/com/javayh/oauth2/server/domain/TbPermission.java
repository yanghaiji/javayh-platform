package com.javayh.oauth2.server.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbPermission implements Serializable {

    private Long id;

    /**
     * 父权限
     */
    private Long parentId;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限英文名称
     */
    private String enname;

    /**
     * 备注
     */
    private String description;

    private Date created;

    private Date updated;

    private static final long serialVersionUID = 1L;
}