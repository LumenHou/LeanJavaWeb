package com.lean.lumen.model;

import lombok.Data;

@Data
public class User {

    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmt_Created;
    private Long gmt_modified;
    private String avatar_url;
}
